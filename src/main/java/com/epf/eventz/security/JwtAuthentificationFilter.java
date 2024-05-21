package com.epf.eventz.security;

import com.epf.eventz.dao.UtilisateurDAO;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.exception.TokenExpiredException;
import com.epf.eventz.model.Jwt;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.model.UtilisateurSecurity;
import com.epf.eventz.service.CustomUserDetailsService;
import com.epf.eventz.service.JwtService;
import com.epf.eventz.service.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.*;

public class JwtAuthentificationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = "";
        Cookie[] cookies = request.getCookies();
        String requestURL = request.getRequestURL().toString();
        String expectedSuffix = "eventz/auth/login";
        if (requestURL.endsWith(expectedSuffix)) {
            Cookie jwtCookie = new Cookie("JwtToken", null);
            jwtCookie.setMaxAge(0);
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);
            cookies=null;
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JwtToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    if (jwtGenerator.isTokenExpired(token) && jwtGenerator.isAdressIpcorrect(token, request)) { //PERMET D'ETENDRE LA DUREE DE LA SESSION
                        jwtService.setLogoutStatusByValue(token, false);
                        String username = jwtGenerator.getUsernameFromExpiredJwt(token);
                        Optional<Utilisateur> utilisateurOptional = utilisateurDAO.findByUsername(username);
                        if (utilisateurOptional.isPresent()) {
                            Utilisateur utilisateur = utilisateurOptional.get();
                            UtilisateurSecurity utilisateurSecurity = new UtilisateurSecurity(utilisateur);
                            UsernamePasswordAuthenticationToken authenticationToken =
                                    new UsernamePasswordAuthenticationToken(
                                            utilisateurSecurity,
                                            null,
                                            utilisateurSecurity.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                            token = jwtGenerator.generateToken(authenticationToken, request);
                            String cookieString = String.format("JwtToken=%s; SameSite=Strict; HttpOnly; Secure; Path=/", token);
                            response.addHeader("Set-Cookie", cookieString);
                            logger.info("Nouveau token créé !");
                        }
                    }
                    try {
                        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
                            String username = jwtGenerator.getUsernameFromJwt(token);
                            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                            List<GrantedAuthority> authorities = new ArrayList<>();
                            for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
                                authorities.add(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
                            }
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    authorities
                            );
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    } catch (Exception e) {
                        Cookie jwtCookie = new Cookie("JwtToken", null);
                        jwtCookie.setMaxAge(0);
                        jwtCookie.setPath("/");
                        response.addCookie(jwtCookie);
                        throw e;
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
