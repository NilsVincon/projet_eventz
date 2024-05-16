package com.epf.eventz.persistence;

import com.epf.eventz.dao.EvenementDAO;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Suivre;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.EvenementService;
import com.epf.eventz.service.SuivreService;
import com.epf.eventz.service.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FillDataBase {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    String mdpCrypte1 = encoder.encode("user");
    String mdpCrypte2 = encoder.encode("admin");

    public static void main(String[] args) {
        SpringApplication.run(FillDataBase.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EvenementService evenementService, UtilisateurService utilisateurService, SuivreService suivreService) {
        return args -> {
            evenementService.addEvenement(new Evenement("TechnoFest", "Le festival de techno le plus attendu de l'année",
                    LocalDate.of(2024, 7, 15), LocalDate.of(2024, 7, 20),
                    40.0f, 800));
            evenementService.addEvenement(new Evenement("RockMania", "Un festival de rock explosif avec les meilleurs groupes du moment",
                    LocalDate.of(2024, 8, 10), LocalDate.of(2024, 8, 15),
                    45.0f, 1000));
            evenementService.addEvenement(new Evenement("ElectroWave", "Plongez dans l'univers de l'électro avec des DJs renommés",
                    LocalDate.of(2024, 9, 5), LocalDate.of(2024, 9, 10),
                    35.0f, 600));
            evenementService.addEvenement(new Evenement("IndieFest", "Un festival indie pour découvrir les nouveaux talents de la scène musicale",
                    LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 7),
                    30.0f, 500));
            evenementService.addEvenement(new Evenement("RapCity", "Le rendez-vous des amateurs de rap et de hip-hop",
                    LocalDate.of(2024, 11, 15), LocalDate.of(2024, 11, 20),
                    50.0f, 1200));
            evenementService.addEvenement(new Evenement("MetalFury", "Un festival qui va faire trembler les murs avec du metal puissant",
                    LocalDate.of(2024, 12, 5), LocalDate.of(2024, 12, 10),
                    45.0f, 900));
            evenementService.addEvenement(new Evenement("ReggaeVibes", "Venez ressentir les bonnes vibrations du reggae",
                    LocalDate.of(2025, 1, 15), LocalDate.of(2025, 1, 20),
                    40.0f, 700));
            evenementService.addEvenement(new Evenement("PunkRiot", "Un festival punk rebelle et plein d'énergie",
                    LocalDate.of(2025, 2, 10), LocalDate.of(2025, 2, 15),
                    35.0f, 800));
            evenementService.addEvenement(new Evenement("HardcoreMadness", "Le festival ultime pour les fans de hardcore",
                    LocalDate.of(2025, 3, 5), LocalDate.of(2025, 3, 10),
                    50.0f, 1000));
            evenementService.addEvenement(new Evenement("DubstepFrenzy", "Plongez dans l'univers hypnotique du dubstep",
                    LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 7),
                    45.0f, 900));
            evenementService.addEvenement(new Evenement("PopExplosion", "Un festival pop coloré et plein de surprises",
                    LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 20),
                    40.0f, 800));
            evenementService.addEvenement(new Evenement("SkaCarnival", "Le carnaval du ska pour danser toute la nuit",
                    LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 15),
                    35.0f, 700));
            evenementService.addEvenement(new Evenement("JazzFusion", "Un festival de jazz qui mélange les genres et les influences",
                    LocalDate.of(2025, 7, 5), LocalDate.of(2025, 7, 10),
                    45.0f, 900));
            evenementService.addEvenement(new Evenement("FunkGroove", "Plongez dans l'univers groovy du funk et de la soul",
                    LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 7),
                    40.0f, 800));
            evenementService.addEvenement(new Evenement("DiscoFever", "Une fièvre disco pour revivre les années folles",
                    LocalDate.of(2025, 9, 15), LocalDate.of(2025, 9, 20),
                    35.0f, 700));
            Utilisateur utilisateur1 = new Utilisateur("Nadiejoa", "Augustin", "augustin.nadiejoa@epfedu.fr", mdpCrypte1, "user", "Homme", "USER", LocalDate.of(2002, 3, 5), "Etudiant Ingénieur Informatique ");
            Utilisateur utilisateur3 = new Utilisateur("Andreani", "Xavier", "jane.doe@example.com", mdpCrypte2, "admin", "Homme", "ADMIN", LocalDate.of(1985, 9, 20), "Description de Jane Doe");
            utilisateurService.creerUtilisateur(utilisateur1);
            utilisateurService.creerUtilisateur(utilisateur3);
            Optional<Utilisateur> moiOptional = utilisateurService.trouverUtilisateurAvecname("Nils75");
            if (moiOptional.isPresent()) {
                Utilisateur moi = moiOptional.get();
                suivreService.creerSuivre(new Suivre(moi, utilisateur1));
                suivreService.creerSuivre(new Suivre(utilisateur3, moi));
            }
        };
    }
}