package com.epf.eventz.servlet;

import com.epf.eventz.dao.UtilisateurDAO;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.model.UtilisateurSecurity;
/*import com.epf.eventz.security.JwtAuthentificationEntryPoint;*/
import com.epf.eventz.security.JwtGenerator;
import com.epf.eventz.service.EmailService;
import com.epf.eventz.service.JwtService;
import com.epf.eventz.service.UtilisateurService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/eventz/auth")
public class AuthentificationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UtilisateurDAO userDAO;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EmailService emailService;

    @GetMapping
    public Iterable<Utilisateur> findAll() {
        return userDAO.findAll();
    }

    @GetMapping("/login")
    public String loginPage() {
        return "authentification/login";
    }



    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> connexion(@ModelAttribute Utilisateur utilisateur, HttpServletResponse response, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(utilisateur.getUsername(),
                    utilisateur.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication, request);
            String cookieString = String.format("JwtToken=%s; SameSite=Strict; HttpOnly; Secure; Path=/", token);
            response.addHeader("Set-Cookie", cookieString);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/eventz/home");

            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect.");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur d'authentification.");
        }
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
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        if (Objects.equals(utilisateur.getRole_utilisateur(), "ADMIN")) {
            utilisateur.setRole_utilisateur("ADMIN,USER");
        }
        try {
            utilisateurService.creerUtilisateur(utilisateur);
            String bodyBienvenue = "Bonjour " + utilisateur.getPrenom_utilisateur() + " " + utilisateur.getNom_utilisateur() + ",\n\n"
                    + "Nous sommes ravis que vous ayez choisi Eventz pour passer vos meilleures soirées !\n"
                    + "Notre équipe est là pour vous accompagner et vous aider à organiser des événements inoubliables.\n\n"
                    + "N'hésitez pas à nous contacter si vous avez la moindre question ou si vous avez besoin d'aide.\n\n"
                    + "Bienvenue dans la communauté Eventz !\n\n"
                    + "Cordialement,\n\n"
                    + "L'équipe Eventz";
            emailService.sendEmail(utilisateur.getEmail_utilisateur(),"Bienvenue chez Eventz !!",bodyBienvenue);
            response.setHeader("Location", "/eventz/auth/login");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }


}