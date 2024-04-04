package com.epf.eventz.service;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Utilisateur;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordManager {


        private BCryptPasswordEncoder passwordEncoder;

        public PasswordManager() {
            this.passwordEncoder = new BCryptPasswordEncoder();
        }
        public String PasswordEncoder(String password){
            return (passwordEncoder.encode(password));
        }
        public boolean authenticate(String email, String password) throws ServiceException {
            Utilisateur utilisateur = UtilisateurService.trouverUtilisateurAvecEmail(email);
            if (utilisateur == null) {
                return false;
            }
            String hashedPassword = passwordEncoder.encode(password);

            return passwordEncoder.matches(password, utilisateur.getMdp_utilisateur());
        }
    }
