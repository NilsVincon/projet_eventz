package com.epf.eventz.security;

import com.epf.eventz.dao.JwtDAO;
import com.epf.eventz.exception.TokenExpiredException;
import com.epf.eventz.model.Jwt;
import com.epf.eventz.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class JwtAuthentificationFilter extends OncePerRequestFilter {

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtDAO jwtDAO;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*String token = getJWTFromRequest(request);*/
        String token = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JwtToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    logger.info("Cookie value : " + token);
                }
            }
        }
        logger.info("token :" + token);
        try {
            if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
                String username = jwtGenerator.getUsernameFromJwt(token);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
                    authorities.add(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
                }
                logger.info("Liste role : " + authorities + " Taille :" + authorities.size());
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        authorities
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            Cookie jwtCookie = new Cookie("JwtToken", null);
            jwtCookie.setMaxAge(0);
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);
            throw e;
        }

        filterChain.doFilter(request, response);
    }

}
