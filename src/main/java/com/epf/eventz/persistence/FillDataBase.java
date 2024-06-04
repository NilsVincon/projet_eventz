package com.epf.eventz.persistence;

import com.epf.eventz.model.*;
import com.epf.eventz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FillDataBase {

    @Autowired
    private EvenementService evenementService;
    @Autowired
    private AdresseService adresseService;
    @Autowired
    private StatutEvenementService statutEvenementService;
    @Autowired
    private TypeEvenementService typeEvenementService;
    @Autowired
    private ArtisteService artisteService;
    @Autowired
    private PrefererArtisteService prefererArtisteService;
    @Autowired
    private ParticipeService participeService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    String mdpCrypte1 = encoder.encode("user");
    String mdpCrypte2 = encoder.encode("admin");

    public static void main(String[] args) {
        SpringApplication.run(FillDataBase.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EvenementService evenementService, UtilisateurService utilisateurService, SuivreService suivreService, ArtisteService artisteService, PrefererArtisteService prefererArtisteService) {
        return args -> {
            //ajout d'un artiste
            File file = new File("src/main/resources/static/images/adrien-laurent.jpeg");
            byte[] imageData = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(imageData);
            }
            File file1 = new File("src/main/resources/static/images/rockfest.png");
            byte[] imageData1 = new byte[(int) file1.length()];
            try (FileInputStream fis1 = new FileInputStream(file1)) {
                fis1.read(imageData1);
            }
            File file2 = new File("src/main/resources/static/images/technofest.jpg");
            byte[] imageData2 = new byte[(int) file2.length()];
            try (FileInputStream fis2 = new FileInputStream(file2)) {
                fis2.read(imageData2);
            }

            List<PrefererArtiste> ListeArtistePref = new ArrayList<PrefererArtiste>();
            List<Performe> ListePerforme = new ArrayList<Performe>();
            List<Jouer> ListeJouer = new ArrayList<Jouer>();

            Artiste artiste = new Artiste();
            artiste.setDescription_artiste("dfdjsflsdj");
            artiste.setNom_artiste("bzbz");
            artiste.setPdpArtiste(imageData);
            artisteService.addArtiste(artiste);

            Artiste playbloi = new Artiste("Playboi Carti", "Artiste de variété française connu pour ses envolées lyriques", ListeArtistePref, ListePerforme, ListeJouer);
            artisteService.addArtiste(playbloi);
            Artiste artiste1 = new Artiste("Jul","Le J c'est le S");
            Artiste artiste2 = new Artiste("Naps","Le J c'est le S");
            Artiste artiste3 = new Artiste("J9","Le J c'est le S");
            Artiste artiste4 = new Artiste("PLK","Le J c'est le S");
            Artiste artiste5 = new Artiste("PNL","Le J c'est le S");
            Artiste artiste6 = new Artiste("ZKR","Le J c'est le S");
            Artiste artiste7 = new Artiste("JackUZI","Le J c'est le S");
            Artiste artiste8 = new Artiste("BenoitLeThug","Le J c'est le S");
            Artiste artiste9 = new Artiste("MichiBOO","Le J c'est le S");
            artisteService.addArtiste(artiste1);
            artisteService.addArtiste(artiste2);
            artisteService.addArtiste(artiste3);
            artisteService.addArtiste(artiste4);
            artisteService.addArtiste(artiste5);
            artisteService.addArtiste(artiste6);
            artisteService.addArtiste(artiste7);
            artisteService.addArtiste(artiste8);
            artisteService.addArtiste(artiste9);

            Utilisateur utilisateur1 = new Utilisateur("Nadiejoa", "Augustin", "augustin.nadiejoa@epfedu.fr", mdpCrypte1, "user", "Homme", "USER", LocalDate.of(2002, 3, 5), "Etudiant Ingénieur Informatique ");
            Utilisateur utilisateur3 = new Utilisateur("Andreani", "Xavier", "jane.doe@example.com", mdpCrypte2, "admin", "Homme", "ADMIN,USER", LocalDate.of(1985, 9, 20), "Description de Jane Doe");
            utilisateur1.setPdpUtilisateur(imageData);
            utilisateur3.setPdpUtilisateur(imageData);
            utilisateurService.creerUtilisateur(utilisateur1);
            utilisateurService.creerUtilisateur(utilisateur3);
            Optional<Utilisateur> moiOptional = utilisateurService.trouverUtilisateurAvecname("user");
            if (moiOptional.isPresent()) {
                Utilisateur moi = moiOptional.get();
                suivreService.creerSuivre(new Suivre(moi, utilisateur3));
                suivreService.creerSuivre(new Suivre(utilisateur3, moi));
                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste, moi));
                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(playbloi, moi));
                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste1, moi));

            }

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
                    40.0f, 800, true);
            technoFest.setAdresse(adresseTechnoFest);
            technoFest.setTypeEvenement(typeEvenementTechnoFest);
            technoFest.setStatutEvenement(statutEvenementTechnoFest);
            technoFest.setPdpEvenement(imageData2);
            technoFest.setOrganisateur(utilisateur1);
            evenementService.addEvenement(technoFest);

            // Ajout de l'événement "RockMania"
            Adresse adresseRockMania = new Adresse();
            adresseRockMania.setNumero_adresse("456");
            adresseRockMania.setRue_adresse("Avenue du Rock");
            adresseRockMania.setVille_adresse("Lyon");
            adresseRockMania.setCode_postal_adresse(12345);
            adresseRockMania.setPays_adresse("France");
            adresseRockMania.setPublic_adresse(false);
            adresseService.creerAdresse(adresseRockMania);

            TypeEvenement typeEvenementRockMania = new TypeEvenement();
            typeEvenementRockMania.setDescription_type_evenement("Musical");
            typeEvenementService.creerTypeEvenement(typeEvenementRockMania);

            StatutEvenement statutEvenementRockMania = new StatutEvenement();
            statutEvenementRockMania.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementRockMania);

            Evenement rockMania = new Evenement("RockMania", "Un festival de rock explosif avec les meilleurs groupes du moment",
                    LocalDate.of(2024, 8, 10), LocalDate.of(2024, 8, 15),
                    45.0f, 1000, true);
            rockMania.setAdresse(adresseRockMania);
            rockMania.setTypeEvenement(typeEvenementRockMania);
            rockMania.setStatutEvenement(statutEvenementRockMania);
            rockMania.setPdpEvenement(imageData1);
            rockMania.setOrganisateur(utilisateur3);
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
                    35.0f, 600, true);
            electroWave.setAdresse(adresseElectroWave);
            electroWave.setTypeEvenement(typeEvenementElectroWave);
            electroWave.setStatutEvenement(statutEvenementElectroWave);
            electroWave.setOrganisateur(utilisateur1);
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
                    30.0f, 500, true);
            indieFest.setAdresse(adresseIndieFest);
            indieFest.setTypeEvenement(typeEvenementIndieFest);
            indieFest.setStatutEvenement(statutEvenementIndieFest);
            indieFest.setOrganisateur(utilisateur3);
            indieFest.setPublic_evenement(true);
            evenementService.addEvenement(indieFest);
            evenementService.addEvenement(new Evenement("RapCity", "Le rendez-vous des amateurs de rap et de hip-hop",
                    LocalDate.of(2024, 11, 15), LocalDate.of(2024, 11, 20),
                    50.0f, 1200, true));
            evenementService.addEvenement(new Evenement("MetalFury", "Un festival qui va faire trembler les murs avec du metal puissant",
                    LocalDate.of(2024, 12, 5), LocalDate.of(2024, 12, 10),
                    45.0f, 900, true));
            evenementService.addEvenement(new Evenement("ReggaeVibes", "Venez ressentir les bonnes vibrations du reggae",
                    LocalDate.of(2025, 1, 15), LocalDate.of(2025, 1, 20),
                    40.0f, 700, true));
            evenementService.addEvenement(new Evenement("PunkRiot", "Un festival punk rebelle et plein d'énergie",
                    LocalDate.of(2025, 2, 10), LocalDate.of(2025, 2, 15),
                    35.0f, 800, true));
            evenementService.addEvenement(new Evenement("HardcoreMadness", "Le festival ultime pour les fans de hardcore",
                    LocalDate.of(2025, 3, 5), LocalDate.of(2025, 3, 10),
                    50.0f, 1000, true));
            evenementService.addEvenement(new Evenement("DubstepFrenzy", "Plongez dans l'univers hypnotique du dubstep",
                    LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 7),
                    45.0f, 900, true));
            evenementService.addEvenement(new Evenement("PopExplosion", "Un festival pop coloré et plein de surprises",
                    LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 20),
                    40.0f, 800, true));
            evenementService.addEvenement(new Evenement("SkaCarnival", "Le carnaval du ska pour danser toute la nuit",
                    LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 15),
                    35.0f, 700, true));
            evenementService.addEvenement(new Evenement("JazzFusion", "Un festival de jazz qui mélange les genres et les influences",
                    LocalDate.of(2025, 7, 5), LocalDate.of(2025, 7, 10),
                    45.0f, 900, true));
            evenementService.addEvenement(new Evenement("FunkGroove", "Plongez dans l'univers groovy du funk et de la soul",
                    LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 7),
                    40.0f, 800, true));
            evenementService.addEvenement(new Evenement("DiscoFever", "Une fièvre disco pour revivre les années folles",
                    LocalDate.of(2025, 9, 15), LocalDate.of(2025, 9, 20),
                    35.0f, 700, true));

            participeService.addParticipe(new Participe(technoFest, utilisateur1));

        };
    }
}
