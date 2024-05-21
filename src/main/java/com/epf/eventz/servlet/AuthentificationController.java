package com.epf.eventz.servlet;

import com.epf.eventz.dao.UtilisateurDAO;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.model.UtilisateurSecurity;
/*import com.epf.eventz.security.JwtAuthentificationEntryPoint;*/
import com.epf.eventz.security.JwtGenerator;
import com.epf.eventz.service.JwtService;
import com.epf.eventz.service.UtilisateurService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/eventz/auth")
public class AuthentificationController {


    private AuthenticationManager authenticationManager;
    private UtilisateurDAO userDAO;
    private UtilisateurService utilisateurService;
    private PasswordEncoder passwordEncoder;

    private JwtGenerator jwtGenerator;
    private JwtService jwtService;

    @Autowired
    public AuthentificationController(AuthenticationManager authenticationManager, UtilisateurDAO userDAO, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, JwtService jwtService, UtilisateurService utilisateurService) {
        this.authenticationManager = authenticationManager;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.jwtService = jwtService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public Iterable<Utilisateur> findAll() {
        return userDAO.findAll();
    }

    @GetMapping("/login")
    public String loginPage() {
        return "authentification/login";
    }


    @PostMapping("/login")
    public void connexion(@ModelAttribute Utilisateur utilisateur, HttpServletResponse response, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(utilisateur.getUsername(),
                utilisateur.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication,request);
        String cookieString = String.format("JwtToken=%s; SameSite=Strict; HttpOnly; Secure; Path=/", token);
        response.addHeader("Set-Cookie", cookieString);
        response.setHeader("Location", "/eventz/home");
        response.setStatus(HttpStatus.FOUND.value());
        log.info("Connexion réussi !");
    }

    @GetMapping("/logout")
    @Transactional
    public void deconnexion(HttpServletResponse response) {
        UtilisateurSecurity securityUser = (UtilisateurSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jwtService.setLogoutStatusByUsername(securityUser.getUsername(), false);
        Cookie cookie = new Cookie("JwtToken", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
        response.setHeader("Location", "/eventz/home");
        response.setStatus(HttpStatus.FOUND.value());
    }


    @GetMapping("/register")
    public String registerPage() {
        return "authentification/register";
    }

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public void inscription(@ModelAttribute Utilisateur utilisateur, HttpServletResponse response) {
        log.info("Utilisateur récupérer : " + utilisateur);
        if (userDAO.existsByUsername(utilisateur.getUsername())) {
            log.error("Username is taken");
        }
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        if (Objects.equals(utilisateur.getRole_utilisateur(), "ADMIN")) {
            utilisateur.setRole_utilisateur("ADMIN,USER");
        }
        try {
            utilisateurService.creerUtilisateur(utilisateur);
            response.setHeader("Location", "/eventz/auth/login");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }


}