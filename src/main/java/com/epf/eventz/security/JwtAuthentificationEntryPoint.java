package com.epf.eventz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthentificationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthentificationEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        int statusCode;
        String errorMessage;

        if (authException instanceof BadCredentialsException) {
            statusCode = HttpServletResponse.SC_UNAUTHORIZED; // 401
            errorMessage = "Mauvais nom d'utilisateur ou mot de passe";
        } else if (authException instanceof DisabledException) {
            statusCode = HttpServletResponse.SC_FORBIDDEN; // 403
            errorMessage = "Vous n'avez pas le droit d'accéder à cette ressource";
        } else {
            statusCode = HttpServletResponse.SC_UNAUTHORIZED; // 401 as default
            errorMessage = authException.getMessage() != null ? authException.getMessage() : "Vous devez être connecté";
            response.sendRedirect(request.getContextPath() + "/eventz/auth/login");
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(statusCode);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", statusCode);
        body.put("message", errorMessage);
        body.put("path", request.getServletPath());

        try (OutputStream outputStream = response.getOutputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputStream, body);
        }
    }
}
