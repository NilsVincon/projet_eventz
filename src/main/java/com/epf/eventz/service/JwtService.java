package com.epf.eventz.service;

import com.epf.eventz.dao.JwtDAO;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class JwtService {

    private final JwtDAO jwtDAO;

    @Autowired
    public JwtService(JwtDAO jwtDAO) {
        this.jwtDAO = jwtDAO;
    }

    @Transactional
    public void supprimerJwtInactifs() {
        jwtDAO.deleteInactifJwt();
    }

    public void ajoute(Jwt jwt) {
        jwtDAO.save(jwt);
    }

    public Jwt trouveParValeur(String jwt) {
        return jwtDAO.findByValeur(jwt);
    }

    @Transactional
    public void setLogoutStatusByUsername(String username, boolean actif) {
        jwtDAO.setLogoutStatus(username, actif);
    }


    @Transactional
    public void setLogoutStatusByValue(String valeur, boolean actif) {
        jwtDAO.setLogoutStatusByValue(valeur, actif);
    }

}