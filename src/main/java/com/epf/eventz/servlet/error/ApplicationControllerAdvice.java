package com.epf.eventz.servlet.error;

import com.epf.eventz.exception.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(value = BadCredentialsException.class)
    public @ResponseBody ProblemDetail BadCredentialsException(BadCredentialsException e){
        log.error(e.getMessage(),e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                UNAUTHORIZED,
                "Identifiants incorrects"
        );
        problemDetail.setProperty("erreur","nous n'avons pas pu vous identifier");
        return problemDetail;
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
    public @ResponseBody ProblemDetail AuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e){
        log.error(e.getMessage(),e);
        return ProblemDetail.forStatusAndDetail(
                UNAUTHORIZED,
                "Token est incorrect "
        );
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public @ResponseBody ProblemDetail AccessDeniedException(AccessDeniedException ae){
        log.error(ae.getMessage(),ae);
        return ProblemDetail.forStatusAndDetail(
                FORBIDDEN,
                "Vous n'avez pas le droit d'accéder à cette ressources"
        );
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(value = Exception.class)
    public Map<String,String> exceptionHandler(Exception e){
        return Map.of("erreur",e.getMessage());
    }
}
