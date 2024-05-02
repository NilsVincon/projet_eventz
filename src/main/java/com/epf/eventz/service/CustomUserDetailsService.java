package com.epf.eventz.service;

import com.epf.eventz.dao.UtilisateurDAO;
import com.epf.eventz.model.UtilisateurSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurDAO userDAO;

    public CustomUserDetailsService(UtilisateurDAO userDAO){
        this.userDAO=userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO
                .findByUsername(username)
                .map(UtilisateurSecurity::new)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found "+username));
    }
}