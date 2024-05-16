package com.epf.eventz.persistence;

import com.epf.eventz.dao.EvenementDAO;
import com.epf.eventz.model.*;
import com.epf.eventz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final EvenementService evenementService;
    private final AdresseService adresseService;
    private final StatutEvenementService statutEvenementService;
    private final TypeEvenementService typeEvenementService;

    @Autowired
    public FillDataBase(EvenementService evenementService,AdresseService adresseService,StatutEvenementService statutEvenementService,TypeEvenementService typeEvenementService) {
        this.evenementService = evenementService;
        this.adresseService=adresseService;
        this.statutEvenementService=statutEvenementService;
        this.typeEvenementService=typeEvenementService;
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    String mdpCrypte1 = encoder.encode("user");
    String mdpCrypte2 = encoder.encode("admin");

    public static void main(String[] args) {
        SpringApplication.run(FillDataBase.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EvenementService evenementService, UtilisateurService utilisateurService, SuivreService suivreService) {
        return args -> {
            // Ajout de l'événement "TechnoFest"
            Adresse adresseTechnoFest = new Adresse();
            adresseTechnoFest.setNumero_adresse("123");
            adresseTechnoFest.setRue_adresse("Rue de la Techno");
            adresseTechnoFest.setVille_adresse("Paris");
            adresseTechnoFest.setCode_postal_adresse(75000);
            adresseTechnoFest.setPays_adresse("France");
            adresseTechnoFest.setPublic_adresse(true);
            adresseService.creerAdresse(adresseTechnoFest);

            TypeEvenement typeEvenementTechnoFest = new TypeEvenement();
            typeEvenementTechnoFest.setDescription_type_evenement("Musical");
            typeEvenementService.creerTypeEvenement(typeEvenementTechnoFest);

            StatutEvenement statutEvenementTechnoFest = new StatutEvenement();
            statutEvenementTechnoFest.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementTechnoFest);

            Evenement technoFest = new Evenement("TechnoFest", "Le festival de techno le plus attendu de l'année",
                    LocalDate.of(2024, 7, 15), LocalDate.of(2024, 7, 20),
                    40.0f, 800);
            technoFest.setAdresse(adresseTechnoFest);
            technoFest.setTypeEvenement(typeEvenementTechnoFest);
            technoFest.setStatutEvenement(statutEvenementTechnoFest);
            evenementService.addEvenement(technoFest);

            // Ajout de l'événement "RockMania"
            Adresse adresseRockMania = new Adresse();
            adresseRockMania.setNumero_adresse("456");
            adresseRockMania.setRue_adresse("Avenue du Rock");
            adresseRockMania.setVille_adresse("Lyon");
            adresseRockMania.setCode_postal_adresse(12345);
            adresseRockMania.setPays_adresse("France");
            adresseRockMania.setPublic_adresse(true);
            adresseService.creerAdresse(adresseRockMania);

            TypeEvenement typeEvenementRockMania = new TypeEvenement();
            typeEvenementRockMania.setDescription_type_evenement("Musical");
            typeEvenementService.creerTypeEvenement(typeEvenementRockMania);

            StatutEvenement statutEvenementRockMania = new StatutEvenement();
            statutEvenementRockMania.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementRockMania);

            Evenement rockMania = new Evenement("RockMania", "Un festival de rock explosif avec les meilleurs groupes du moment",
                    LocalDate.of(2024, 8, 10), LocalDate.of(2024, 8, 15),
                    45.0f, 1000);
            rockMania.setAdresse(adresseRockMania);
            rockMania.setTypeEvenement(typeEvenementRockMania);
            rockMania.setStatutEvenement(statutEvenementRockMania);
            evenementService.addEvenement(rockMania);

            // Ajout de l'événement "ElectroWave"
            Adresse adresseElectroWave = new Adresse();
            adresseElectroWave.setNumero_adresse("789");
            adresseElectroWave.setRue_adresse("Rue de l'Électro");
            adresseElectroWave.setVille_adresse("Marseille");
            adresseElectroWave.setCode_postal_adresse(10115);
            adresseElectroWave.setPays_adresse("France");
            adresseElectroWave.setPublic_adresse(true);
            adresseService.creerAdresse(adresseElectroWave);

            TypeEvenement typeEvenementElectroWave = new TypeEvenement();
            typeEvenementElectroWave.setDescription_type_evenement("techno");
            typeEvenementService.creerTypeEvenement(typeEvenementElectroWave);


            StatutEvenement statutEvenementElectroWave = new StatutEvenement();
            statutEvenementElectroWave.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementElectroWave);

            Evenement electroWave = new Evenement("ElectroWave", "Plongez dans l'univers de l'électro avec des DJs renommés",
                    LocalDate.of(2024, 9, 5), LocalDate.of(2024, 9, 10),
                    35.0f, 600);
            electroWave.setAdresse(adresseElectroWave);
            electroWave.setTypeEvenement(typeEvenementElectroWave);
            electroWave.setStatutEvenement(statutEvenementElectroWave);
            evenementService.addEvenement(electroWave);

            // Ajout de l'événement "IndieFest"
            Adresse adresseIndieFest = new Adresse();
            adresseIndieFest.setNumero_adresse("345");
            adresseIndieFest.setRue_adresse("Rue de l'Indie");
            adresseIndieFest.setVille_adresse("Paris");
            adresseIndieFest.setCode_postal_adresse(75015);
            adresseIndieFest.setPays_adresse("France");
            adresseIndieFest.setPublic_adresse(true);
            adresseService.creerAdresse(adresseIndieFest);

            TypeEvenement typeEvenementIndieFest = new TypeEvenement();
            typeEvenementIndieFest.setDescription_type_evenement("Musical");
            typeEvenementService.creerTypeEvenement(typeEvenementIndieFest);

            StatutEvenement statutEvenementIndieFest = new StatutEvenement();
            statutEvenementIndieFest.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementIndieFest);

            Evenement indieFest = new Evenement("IndieFest", "Un festival indie pour découvrir les nouveaux talents de la scène musicale",
                    LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 7),
                    30.0f, 500);
            indieFest.setAdresse(adresseIndieFest);
            indieFest.setTypeEvenement(typeEvenementIndieFest);
            indieFest.setStatutEvenement(statutEvenementIndieFest);
            evenementService.addEvenement(indieFest);

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