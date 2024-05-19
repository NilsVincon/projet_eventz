package com.epf.eventz.security;


import com.epf.eventz.exception.TokenExpiredException;
import com.epf.eventz.model.Jwt;
import com.epf.eventz.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Component
public class JwtGenerator {

    private JwtService jwtService;

    public String generateToken(Authentication authentication, HttpServletRequest request) {
        String username = authentication.getName();
        Date currentdate = new Date();
        Date expiredate = new Date(currentdate.getTime() + SecurityConstants.JWT_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
                .compact();
        String ipAddress = "";
        if(request != null){
            ipAddress = request.getRemoteAddr();
        }
        Jwt jwt = Jwt.builder()
                .valeur(token)
                .expireDate(expiredate)
                .actif(true)
                .username(username)
                .adresseip(ipAddress)
                .build();
        this.jwtService.ajoute(jwt);
        return token;
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        if(isTokenExpired(token)){
            throw new AuthenticationCredentialsNotFoundException("Le token est expiré ");
        }
        try {
            Jwts
                    .parser()
                    .setSigningKey(SecurityConstants.JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Le token est incorrect");
        }
    }


    public Date getExpirationDateFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    public String getUsernameFromExpiredJwt(String token) {
        String username = jwtService.trouveParValeur(token).getUsername();
        return username;
    }

    public boolean isAdressIpcorrect(String token,HttpServletRequest request){
        String adresseIpToken = jwtService.trouveParValeur(token).getAdresseip();
        String adresseIpActuel = request.getRemoteAddr();
        return Objects.equals(adresseIpToken, adresseIpActuel);
    }


    public boolean isTokenExpired(String token) {
        Date expirationDate = jwtService.trouveParValeur(token).getExpireDate();
        return expirationDate.before(new Date());
    }

}
