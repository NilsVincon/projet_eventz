package com.epf.eventz.security;

import com.epf.eventz.dao.JwtDAO;
import com.epf.eventz.model.Jwt;
import com.epf.eventz.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JwtAuthentificationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtDAO jwtDAO;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJWTFromRequest(request);
        Cookie[] cookies = request.getCookies();
        if (cookies ==null){
            logger.error("Vous n'êtes pas connecté");
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JwtToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    logger.info("Cookie value : "+token);
                }
            }
        }
        logger.info("token :"+token);
        if(StringUtils.hasText(token) && jwtGenerator.validateToken(token)){
            Jwt token_dans_la_bdd = jwtDAO.findByValeur(token);
            String username = jwtGenerator.getUsernameFromJwt(token);
            String username_dans_la_bdd = token_dans_la_bdd.getUsername();
            if (!Objects.equals(username_dans_la_bdd, username)){
                logger.error("Le token fournis ne correspond pas");
            }
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
            }
            logger.info("Liste role : "+authorities+" Taille :"+authorities.size());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    authorities
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request){
        String bearrerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearrerToken) && bearrerToken.startsWith("Bearer ")){
            return bearrerToken.substring(7,bearrerToken.length());
        }
        return null;
    }
}
