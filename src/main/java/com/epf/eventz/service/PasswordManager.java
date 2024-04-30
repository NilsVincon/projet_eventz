package com.epf.eventz.service;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Utilisateur;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        public boolean isPasswordSafe(String password){
            if (password.length() < 8) {
                return false;
            }

            Pattern patternSpecial = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcherSpecial = patternSpecial.matcher(password);
            if (!matcherSpecial.find()) {
                return false;
            }

            Pattern patternDigit = Pattern.compile("[0-9]");
            Matcher matcherDigit = patternDigit.matcher(password);
            if (!matcherDigit.find()) {
                return false;
            }

            Pattern patternLowercase = Pattern.compile("[a-z]");
            Matcher matcherLowercase = patternLowercase.matcher(password);
            if (!matcherLowercase.find()) {
                return false;
            }

            Pattern patternUppercase = Pattern.compile("[A-Z]");
            Matcher matcherUppercase = patternUppercase.matcher(password);
            if (!matcherUppercase.find()) {
                return false;
            }

            String[] motsDePasseCourants = {"password", "123456", "qwerty", "abc123"};
            for (String passwordCourant : motsDePasseCourants) {
                if (passwordCourant.equals(password.toLowerCase())) {
                    return false;
                }
            }

            // Si toutes les conditions sont remplies, le mot de passe est considéré comme sécurisé
            return true;
        }
    }
