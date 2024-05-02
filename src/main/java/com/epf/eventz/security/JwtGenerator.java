package com.epf.eventz.security;


import com.epf.eventz.dao.JwtDAO;
import com.epf.eventz.model.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Component
public class JwtGenerator {

    private JwtDAO jwtDAO;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentdate = new Date();
        Date expiredate = new Date(currentdate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredate)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.JWT_SECRET)
                .compact();
        Jwt jwt = Jwt.builder()
                .valeur(token)
                .expireDate(expiredate)
                .actif(true)
                .username(username)
                .build();
        this.jwtDAO.save(jwt);
        return token;
    }

    public String getUsernameFromJwt(String token){
        Claims claims=Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(SecurityConstants.JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex){
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }

}
