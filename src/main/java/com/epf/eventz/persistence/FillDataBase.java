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
    @Autowired
    private JouerService jouerService;
    @Autowired
    private PerformeService performeService;
    @Autowired
    private NoterService noterService;

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
            File file = new File("src/main/resources/static/images/xavier.jpg");
            byte[] xavierpdp = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(xavierpdp);
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

            File file3 = new File("src/main/resources/static/images/nils.jpg");
            byte[] nilspdp = new byte[(int) file3.length()];
            try (FileInputStream fis3 = new FileInputStream(file3)) {
                fis3.read(nilspdp);
            }
            File file4 = new File("src/main/resources/static/images/aziz__profil.jpg");
            byte[] azizpdp = new byte[(int) file4.length()];
            try (FileInputStream fis4 = new FileInputStream(file4)) {
                fis4.read(azizpdp);
            }
            File file6 = new File("src/main/resources/static/images/davidguettapdp.jpeg");
            byte[] davidguettapdp = new byte[(int) file6.length()];
            try (FileInputStream fis6 = new FileInputStream(file6)) {
                fis6.read(davidguettapdp);
            }
            File file7 = new File("src/main/resources/static/images/djsnakepdp.jpeg");
            byte[] djsnakepdp = new byte[(int) file7.length()];
            try (FileInputStream fis7 = new FileInputStream(file7)) {
                fis7.read(djsnakepdp);
            }
            File file8 = new File("src/main/resources/static/images/julpdp.jpeg");
            byte[] julpdp = new byte[(int) file8.length()];
            try (FileInputStream fis8 = new FileInputStream(file8)) {
                fis8.read(julpdp);
            }
            File file9 = new File("src/main/resources/static/images/marshmellopdp.webp");
            byte[] marshmellopdp = new byte[(int) file9.length()];
            try (FileInputStream fis9 = new FileInputStream(file9)) {
                fis9.read(marshmellopdp);
            }
            File file10 = new File("src/main/resources/static/images/shakirapdp.jpeg");
            byte[] shakirapdp = new byte[(int) file10.length()];
            try (FileInputStream fis10 = new FileInputStream(file10)) {
                fis10.read(shakirapdp);
            }
            File file11 = new File("src/main/resources/static/images/ghilempdp.png");
            byte[] ghilempdp = new byte[(int) file11.length()];
            try (FileInputStream fis11 = new FileInputStream(file11)) {
                fis11.read(ghilempdp);
            }
            File file12 = new File("src/main/resources/static/images/augustinpdp.png");
            byte[] augustinpdp = new byte[(int) file12.length()];
            try (FileInputStream fis12 = new FileInputStream(file12)) {
                fis12.read(augustinpdp);
            }
            File file13 = new File("src/main/resources/static/images/cecilepdp.png");
            byte[] cecilepdp = new byte[(int) file13.length()];
            try (FileInputStream fis13 = new FileInputStream(file13)) {
                fis13.read(cecilepdp);
            }
            File file14 = new File("src/main/resources/static/images/dominique.webp");
            byte[] dominique = new byte[(int) file14.length()];
            try (FileInputStream fis14 = new FileInputStream(file14)) {
                fis14.read(dominique);
            }
            File file15 = new File("src/main/resources/static/images/sofie.jpeg");
            byte[] sofie = new byte[(int) file15.length()];
            try (FileInputStream fis15 = new FileInputStream(file15)) {
                fis15.read(sofie);
            }

            List<PrefererArtiste> ListeArtistePref = new ArrayList<PrefererArtiste>();
            List<Performe> ListePerforme = new ArrayList<Performe>();
            List<Jouer> ListeJouer = new ArrayList<Jouer>();





            Artiste artiste1 = new Artiste("Jul","Rappeur indépendant français connu pour son style original au sein du paysage musical");
            artiste1.setPdpArtiste(julpdp);
            Artiste artiste2 = new Artiste("Shakira","Chanteuse, compositrice et productrice colombienne s'étant fait connaître avec Whenever, Whatever en 2002");
            artiste2.setPdpArtiste(shakirapdp);
            Artiste artiste3 = new Artiste("David Guetta","DJ français connu pour ces titres dansants depuis 2007");
            artiste3.setPdpArtiste(davidguettapdp);
            Artiste artiste4 = new Artiste("DJ Snake","DJ français s'étant fait connaitre avec Turn Down for What en 2013 et Lean On en 2015");
            artiste4.setPdpArtiste(djsnakepdp);
            Artiste artiste5 = new Artiste("Marshmello","DJ américain masqué connu pour ces titres electro et dubstep");
            artiste5.setPdpArtiste(marshmellopdp);
            artisteService.addArtiste(artiste1);
            artisteService.addArtiste(artiste2);
            artisteService.addArtiste(artiste3);
            artisteService.addArtiste(artiste4);
            artisteService.addArtiste(artiste5);

            Utilisateur utilisateur4 = new Utilisateur("Aziz", "Chelaifa", "hihih.nadiejoa@epfedu.fr", mdpCrypte1, "Aziz", "Homme", "USER", LocalDate.of(2002, 3, 5), "Etudiant Ingénieur Informatique ");
            utilisateur4.setPdpUtilisateur(azizpdp);

            Utilisateur utilisateur1 = new Utilisateur("Nadiejoa", "Augustin", "augustin.nadiejoa@epfedu.fr", mdpCrypte1, "Augustin", "Homme", "USER", LocalDate.of(2002, 8, 9), "Etudiant en Médecine");
            utilisateur1.setPdpUtilisateur(augustinpdp);
            Utilisateur utilisateur3 = new Utilisateur("Andreani", "Xavier", "jane.doe@example.com", mdpCrypte2, "Xavier", "Homme", "ADMIN,USER", LocalDate.of(1999, 8, 20), "Etudiant en Art");
            Utilisateur utilisateur2 = new Utilisateur("Guilhem", "Versailles", "jane.d@example.com", mdpCrypte2, "ghuilem", "Homme", "ADMIN,USER", LocalDate.of(1980, 3, 17), "Etudiant Ingénieur Informatique");
            utilisateur3.setPdpUtilisateur(xavierpdp);

            utilisateur2.setPdpUtilisateur(ghilempdp);
            Utilisateur utilisateur5 = new Utilisateur("Robineau", "Cécile", "cecile.robineau@yahoo.fr", mdpCrypte1, "Cécile", "Femme", "ADMIN,USER", LocalDate.of(1995, 9, 20), "Je cherche des événements sur Paris :)");
            utilisateur5.setPdpUtilisateur(cecilepdp);
            Utilisateur utilisateur6 = new Utilisateur("Genou", "Sophie", "sophie.genou@gmail.com", mdpCrypte1, "Sophie", "Femme", "ADMIN,USER", LocalDate.of(1989, 9, 27), "Nouvelle sur le site, je cherche des visites de musées");
            utilisateur6.setPdpUtilisateur(sofie);
            utilisateurService.creerUtilisateur(utilisateur1);
            utilisateurService.creerUtilisateur(utilisateur4);
            utilisateurService.creerUtilisateur(utilisateur3);
            utilisateurService.creerUtilisateur(utilisateur5);
            utilisateurService.creerUtilisateur(utilisateur6);
            Utilisateur utilisateur7 = new Utilisateur("polia", "dominique", "hihih.nadiejoa@epfedu.fr", mdpCrypte1, "dominique", "Femme", "USER", LocalDate.of(2001, 3, 5), " médecin cardiologue  ");
            utilisateur7.setPdpUtilisateur(dominique);
            Utilisateur utilisateur8 = new Utilisateur("vincon", "nils", "hihih.nadiejoa@epfedu.fr", mdpCrypte1, "nils", "Homme", "USER", LocalDate.of(2001, 3, 5), " médecin cardiologue  ");
            utilisateur8.setPdpUtilisateur(nilspdp);
            utilisateurService.creerUtilisateur(utilisateur8);

            utilisateurService.creerUtilisateur(utilisateur7);

            utilisateurService.creerUtilisateur(utilisateur2);
            utilisateurService.creerUtilisateur(utilisateur4);
            utilisateurService.creerUtilisateur(utilisateur5);
            utilisateurService.creerUtilisateur(utilisateur6);


                suivreService.creerSuivre(new Suivre(utilisateur2, utilisateur3));
                suivreService.creerSuivre(new Suivre(utilisateur3, utilisateur2));
                suivreService.creerSuivre(new Suivre(utilisateur2, utilisateur1));
                suivreService.creerSuivre(new Suivre(utilisateur1, utilisateur2));
                suivreService.creerSuivre(new Suivre(utilisateur1, utilisateur3));
                suivreService.creerSuivre(new Suivre(utilisateur3, utilisateur1));
                suivreService.creerSuivre(new Suivre(utilisateur1, utilisateur4));
                suivreService.creerSuivre(new Suivre(utilisateur4, utilisateur1));
                suivreService.creerSuivre(new Suivre(utilisateur2, utilisateur4));
                suivreService.creerSuivre(new Suivre(utilisateur4, utilisateur2));
            suivreService.creerSuivre(new Suivre(utilisateur8, utilisateur4));
            suivreService.creerSuivre(new Suivre(utilisateur4, utilisateur8));
            suivreService.creerSuivre(new Suivre(utilisateur8, utilisateur2));
            suivreService.creerSuivre(new Suivre(utilisateur2, utilisateur8));
            suivreService.creerSuivre(new Suivre(utilisateur8, utilisateur2));
            suivreService.creerSuivre(new Suivre(utilisateur3, utilisateur8));
                suivreService.creerSuivre(new Suivre(utilisateur2, utilisateur5));
                suivreService.creerSuivre(new Suivre(utilisateur5, utilisateur2));
                suivreService.creerSuivre(new Suivre(utilisateur2, utilisateur6));


                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste2, utilisateur1));

                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste1, utilisateur1));
                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste3, utilisateur2));
                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste5, utilisateur2));
                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste2, utilisateur3));
                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste4, utilisateur3));
                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste3, utilisateur4));
                prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste2, utilisateur4));
            prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste3, utilisateur7));
            prefererArtisteService.creerPrefererArtiste(new PrefererArtiste(artiste2, utilisateur7));


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
            typeEvenementTechnoFest.setDescription_type_evenement(TypeEvenementEnum.Festival);
            typeEvenementService.creerTypeEvenement(typeEvenementTechnoFest);

            StatutEvenement statutEvenementTechnoFest = new StatutEvenement();
            statutEvenementTechnoFest.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementTechnoFest);

            Evenement technoFest = new Evenement("TechnoFest", "Le festival de techno le plus attendu de l'année",
                    LocalDate.of(2024, 6, 13), LocalDate.of(2024, 7, 20),
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
            typeEvenementRockMania.setDescription_type_evenement(TypeEvenementEnum.Soirée);
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
            typeEvenementElectroWave.setDescription_type_evenement(TypeEvenementEnum.Festival);
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
            typeEvenementIndieFest.setDescription_type_evenement(TypeEvenementEnum.Musée);
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

            participeService.addParticipe(new Participe(technoFest, utilisateur1));

            participeService.addParticipe(new Participe(technoFest, utilisateur3));
            participeService.addParticipe(new Participe(technoFest, utilisateur2));
            participeService.addParticipe(new Participe(technoFest, utilisateur5));
            participeService.addParticipe(new Participe(technoFest, utilisateur4));
            participeService.addParticipe(new Participe(technoFest, utilisateur6));
            performeService.creer(new Performe(technoFest, artiste1));
            performeService.creer(new Performe(technoFest, artiste4));
            performeService.creer(new Performe(technoFest, artiste2));
            performeService.creer(new Performe(technoFest, artiste3));
            performeService.creer(new Performe(technoFest, artiste4));
            performeService.creer(new Performe(technoFest, artiste5));


            participeService.addParticipe(new Participe(indieFest, utilisateur1));
            participeService.addParticipe(new Participe(indieFest, utilisateur3));
            participeService.addParticipe(new Participe(indieFest, utilisateur2));
            participeService.addParticipe(new Participe(indieFest, utilisateur5));

            performeService.creer(new Performe(indieFest, artiste1));
            performeService.creer(new Performe(indieFest, artiste4));

            participeService.addParticipe(new Participe(electroWave, utilisateur1));
            participeService.addParticipe(new Participe(electroWave, utilisateur7));
            participeService.addParticipe(new Participe(electroWave, utilisateur3));
            participeService.addParticipe(new Participe(electroWave, utilisateur5));

            performeService.creer(new Performe(electroWave, artiste2));
            performeService.creer(new Performe(electroWave, artiste5));

            participeService.addParticipe(new Participe(rockMania, utilisateur1));
            participeService.addParticipe(new Participe(rockMania, utilisateur7));
            participeService.addParticipe(new Participe(rockMania, utilisateur3));
            participeService.addParticipe(new Participe(rockMania, utilisateur5));

            performeService.creer(new Performe(rockMania, artiste2));
            performeService.creer(new Performe(rockMania, artiste5));

            // Ajout de l'événement "RapCity"
            Adresse adresseRapCity = new Adresse();
            adresseRapCity.setNumero_adresse("567");
            adresseRapCity.setRue_adresse("Boulevard du Rap");
            adresseRapCity.setVille_adresse("Nantes");
            adresseRapCity.setCode_postal_adresse(44000);
            adresseRapCity.setPays_adresse("France");
            adresseRapCity.setPublic_adresse(true);
            adresseService.creerAdresse(adresseRapCity);

            TypeEvenement typeEvenementRapCity = new TypeEvenement();
            typeEvenementRapCity.setDescription_type_evenement(TypeEvenementEnum.Musée);
            typeEvenementService.creerTypeEvenement(typeEvenementRapCity);

            StatutEvenement statutEvenementRapCity = new StatutEvenement();
            statutEvenementRapCity.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementRapCity);

            Evenement rapCity = new Evenement("RapCity", "Le rendez-vous des amateurs de rap et de hip-hop",
                    LocalDate.of(2024, 11, 15), LocalDate.of(2024, 11, 20),
                    50.0f, 1200, true);
            rapCity.setAdresse(adresseRapCity);
            rapCity.setTypeEvenement(typeEvenementRapCity);
            rapCity.setStatutEvenement(statutEvenementRapCity);
            rapCity.setOrganisateur(utilisateur2);
            evenementService.addEvenement(rapCity);
            participeService.addParticipe(new Participe(rapCity, utilisateur2));
            participeService.addParticipe(new Participe(rapCity, utilisateur3));
            performeService.creer(new Performe(rapCity, artiste1));
            performeService.creer(new Performe(rapCity, artiste3));

// Ajout de l'événement "MetalFury"
            Adresse adresseMetalFury = new Adresse();
            adresseMetalFury.setNumero_adresse("678");
            adresseMetalFury.setRue_adresse("Rue du Metal");
            adresseMetalFury.setVille_adresse("Bordeaux");
            adresseMetalFury.setCode_postal_adresse(33000);
            adresseMetalFury.setPays_adresse("France");
            adresseMetalFury.setPublic_adresse(true);
            adresseService.creerAdresse(adresseMetalFury);

            TypeEvenement typeEvenementMetalFury = new TypeEvenement();
            typeEvenementMetalFury.setDescription_type_evenement(TypeEvenementEnum.Festival);
            typeEvenementService.creerTypeEvenement(typeEvenementMetalFury);

            StatutEvenement statutEvenementMetalFury = new StatutEvenement();
            statutEvenementMetalFury.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementMetalFury);

            Evenement metalFury = new Evenement("MetalFury", "Un festival qui va faire trembler les murs avec du metal énervé",
                    LocalDate.of(2023, 12, 5), LocalDate.of(2024, 12, 10),
                    45.0f, 900, true);
            metalFury.setAdresse(adresseMetalFury);
            metalFury.setTypeEvenement(typeEvenementMetalFury);
            metalFury.setStatutEvenement(statutEvenementMetalFury);
            metalFury.setOrganisateur(utilisateur4);
            evenementService.addEvenement(metalFury);
            participeService.addParticipe(new Participe(metalFury, utilisateur1));
            participeService.addParticipe(new Participe(metalFury, utilisateur5));
            performeService.creer(new Performe(metalFury, artiste2));
            performeService.creer(new Performe(metalFury, artiste5));

// Ajout de l'événement "ReggaeVibes"
            Adresse adresseReggaeVibes = new Adresse();
            adresseReggaeVibes.setNumero_adresse("789");
            adresseReggaeVibes.setRue_adresse("Avenue du Reggae");
            adresseReggaeVibes.setVille_adresse("Nice");
            adresseReggaeVibes.setCode_postal_adresse(06000);
            adresseReggaeVibes.setPays_adresse("France");
            adresseReggaeVibes.setPublic_adresse(true);
            adresseService.creerAdresse(adresseReggaeVibes);

            TypeEvenement typeEvenementReggaeVibes = new TypeEvenement();
            typeEvenementReggaeVibes.setDescription_type_evenement(TypeEvenementEnum.Festival);
            typeEvenementService.creerTypeEvenement(typeEvenementReggaeVibes);

            StatutEvenement statutEvenementReggaeVibes = new StatutEvenement();
            statutEvenementReggaeVibes.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementReggaeVibes);

            Evenement reggaeVibes = new Evenement("ReggaeVibes", "Festival de reggae de 5 jours, venez profiter de musiques à l'inspiration Jamaïcaine dans un cadre magnifique",
                    LocalDate.of(2023, 1, 15), LocalDate.of(2025, 1, 20),
                    40.0f, 700, true);
            reggaeVibes.setAdresse(adresseReggaeVibes);
            reggaeVibes.setTypeEvenement(typeEvenementReggaeVibes);
            reggaeVibes.setStatutEvenement(statutEvenementReggaeVibes);
            reggaeVibes.setOrganisateur(utilisateur3);
            evenementService.addEvenement(reggaeVibes);
            participeService.addParticipe(new Participe(reggaeVibes, utilisateur2));
            participeService.addParticipe(new Participe(reggaeVibes, utilisateur4));
            performeService.creer(new Performe(reggaeVibes, artiste3));
            performeService.creer(new Performe(reggaeVibes, artiste1));

// Ajout de l'événement "PunkRiot"
            Adresse adressePunkRiot = new Adresse();
            adressePunkRiot.setNumero_adresse("890");
            adressePunkRiot.setRue_adresse("Rue du Punk");
            adressePunkRiot.setVille_adresse("Strasbourg");
            adressePunkRiot.setCode_postal_adresse(67000);
            adressePunkRiot.setPays_adresse("France");
            adressePunkRiot.setPublic_adresse(true);
            adresseService.creerAdresse(adressePunkRiot);

            TypeEvenement typeEvenementPunkRiot = new TypeEvenement();
            typeEvenementPunkRiot.setDescription_type_evenement(TypeEvenementEnum.Musée);
            typeEvenementService.creerTypeEvenement(typeEvenementPunkRiot);

            StatutEvenement statutEvenementPunkRiot = new StatutEvenement();
            statutEvenementPunkRiot.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementPunkRiot);

            Evenement punkRiot = new Evenement("PunkRiot", "Le festival punk rock a ne pas rater",
                    LocalDate.of(2023, 2, 10), LocalDate.of(2025, 2, 15),
                    35.0f, 800, true);
            punkRiot.setAdresse(adressePunkRiot);
            punkRiot.setTypeEvenement(typeEvenementPunkRiot);
            punkRiot.setStatutEvenement(statutEvenementPunkRiot);
            punkRiot.setOrganisateur(utilisateur5);
            evenementService.addEvenement(punkRiot);
            participeService.addParticipe(new Participe(punkRiot, utilisateur1));
            participeService.addParticipe(new Participe(punkRiot, utilisateur6));
            performeService.creer(new Performe(punkRiot, artiste4));
            performeService.creer(new Performe(punkRiot, artiste2));

// Ajout de l'événement "HardcoreMadness"
            Adresse adresseHardcoreMadness = new Adresse();
            adresseHardcoreMadness.setNumero_adresse("91");
            adresseHardcoreMadness.setRue_adresse("Avenue du Hardrock");
            adresseHardcoreMadness.setVille_adresse("Lille");
            adresseHardcoreMadness.setCode_postal_adresse(59000);
            adresseHardcoreMadness.setPays_adresse("France");
            adresseHardcoreMadness.setPublic_adresse(true);
            adresseService.creerAdresse(adresseHardcoreMadness);

            TypeEvenement typeEvenementHardcoreMadness = new TypeEvenement();
            typeEvenementHardcoreMadness.setDescription_type_evenement(TypeEvenementEnum.Festival);
            typeEvenementService.creerTypeEvenement(typeEvenementHardcoreMadness);

            StatutEvenement statutEvenementHardcoreMadness = new StatutEvenement();
            statutEvenementHardcoreMadness.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementHardcoreMadness);

            Evenement hardcoreMadness = new Evenement("HardcoreMadness", "Le festival à ne pas rater pour les fans de musique hardcore",
                    LocalDate.of(2023, 3, 5), LocalDate.of(2025, 3, 10),
                    50.0f, 1000, true);
            hardcoreMadness.setAdresse(adresseHardcoreMadness);
            hardcoreMadness.setTypeEvenement(typeEvenementHardcoreMadness);
            hardcoreMadness.setStatutEvenement(statutEvenementHardcoreMadness);
            hardcoreMadness.setOrganisateur(utilisateur6);
            evenementService.addEvenement(hardcoreMadness);
            participeService.addParticipe(new Participe(hardcoreMadness, utilisateur3));
            participeService.addParticipe(new Participe(hardcoreMadness, utilisateur5));
            performeService.creer(new Performe(hardcoreMadness, artiste5));
            performeService.creer(new Performe(hardcoreMadness, artiste2));

// Ajout de l'événement "DubstepFrenzy"
            Adresse adresseDubstepFrenzy = new Adresse();
            adresseDubstepFrenzy.setNumero_adresse("101");
            adresseDubstepFrenzy.setRue_adresse("Rue du Dubstep");
            adresseDubstepFrenzy.setVille_adresse("Montpellier");
            adresseDubstepFrenzy.setCode_postal_adresse(34000);
            adresseDubstepFrenzy.setPays_adresse("France");
            adresseDubstepFrenzy.setPublic_adresse(true);
            adresseService.creerAdresse(adresseDubstepFrenzy);

            TypeEvenement typeEvenementDubstepFrenzy = new TypeEvenement();
            typeEvenementDubstepFrenzy.setDescription_type_evenement(TypeEvenementEnum.Soirée);
            typeEvenementService.creerTypeEvenement(typeEvenementDubstepFrenzy);

            StatutEvenement statutEvenementDubstepFrenzy = new StatutEvenement();
            statutEvenementDubstepFrenzy.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementDubstepFrenzy);

            Evenement dubstepFrenzy = new Evenement("DubstepFrenzy", "Plongez dans l'univers déjanté du dubstep",
                    LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 7),
                    45.0f, 900, true);
            dubstepFrenzy.setAdresse(adresseDubstepFrenzy);
            dubstepFrenzy.setTypeEvenement(typeEvenementDubstepFrenzy);
            dubstepFrenzy.setStatutEvenement(statutEvenementDubstepFrenzy);
            dubstepFrenzy.setOrganisateur(utilisateur4);
            evenementService.addEvenement(dubstepFrenzy);
            participeService.addParticipe(new Participe(dubstepFrenzy, utilisateur2));
            participeService.addParticipe(new Participe(dubstepFrenzy, utilisateur5));
            performeService.creer(new Performe(dubstepFrenzy, artiste3));
            performeService.creer(new Performe(dubstepFrenzy, artiste1));

// Ajout de l'événement "PopExplosion"
            Adresse adressePopExplosion = new Adresse();
            adressePopExplosion.setNumero_adresse("202");
            adressePopExplosion.setRue_adresse("Boulevard du Pop");
            adressePopExplosion.setVille_adresse("Toulouse");
            adressePopExplosion.setCode_postal_adresse(31000);
            adressePopExplosion.setPays_adresse("France");
            adressePopExplosion.setPublic_adresse(true);
            adresseService.creerAdresse(adressePopExplosion);

            TypeEvenement typeEvenementPopExplosion = new TypeEvenement();
            typeEvenementPopExplosion.setDescription_type_evenement(TypeEvenementEnum.Festival);
            typeEvenementService.creerTypeEvenement(typeEvenementPopExplosion);

            StatutEvenement statutEvenementPopExplosion = new StatutEvenement();
            statutEvenementPopExplosion.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementPopExplosion);

            Evenement popExplosion = new Evenement("PopExplosion", "Un festival pop coloré et plein de surprises",
                    LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 20),
                    40.0f, 800, true);
            popExplosion.setAdresse(adressePopExplosion);
            popExplosion.setTypeEvenement(typeEvenementPopExplosion);
            popExplosion.setStatutEvenement(statutEvenementPopExplosion);
            popExplosion.setOrganisateur(utilisateur6);
            evenementService.addEvenement(popExplosion);
            participeService.addParticipe(new Participe(popExplosion, utilisateur3));
            participeService.addParticipe(new Participe(popExplosion, utilisateur5));
            performeService.creer(new Performe(popExplosion, artiste1));
            performeService.creer(new Performe(popExplosion, artiste4));

// Ajout de l'événement "SkaCarnival"
            Adresse adresseSkaCarnival = new Adresse();
            adresseSkaCarnival.setNumero_adresse("303");
            adresseSkaCarnival.setRue_adresse("Avenue du Ska");
            adresseSkaCarnival.setVille_adresse("Lille");
            adresseSkaCarnival.setCode_postal_adresse(59000);
            adresseSkaCarnival.setPays_adresse("France");
            adresseSkaCarnival.setPublic_adresse(true);
            adresseService.creerAdresse(adresseSkaCarnival);

            TypeEvenement typeEvenementSkaCarnival = new TypeEvenement();
            typeEvenementSkaCarnival.setDescription_type_evenement(TypeEvenementEnum.Festival);
            typeEvenementService.creerTypeEvenement(typeEvenementSkaCarnival);

            StatutEvenement statutEvenementSkaCarnival = new StatutEvenement();
            statutEvenementSkaCarnival.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementSkaCarnival);

            Evenement skaCarnival = new Evenement("SkaCarnival", "Le carnaval du ska pour danser toute la nuit",
                    LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 15),
                    35.0f, 700, true);
            skaCarnival.setAdresse(adresseSkaCarnival);
            skaCarnival.setTypeEvenement(typeEvenementSkaCarnival);
            skaCarnival.setStatutEvenement(statutEvenementSkaCarnival);
            skaCarnival.setOrganisateur(utilisateur1);
            evenementService.addEvenement(skaCarnival);
            participeService.addParticipe(new Participe(skaCarnival, utilisateur1));
            participeService.addParticipe(new Participe(skaCarnival, utilisateur3));
            performeService.creer(new Performe(skaCarnival, artiste5));
            performeService.creer(new Performe(skaCarnival, artiste2));

// Ajout de l'événement "JazzFusion"
            Adresse adresseJazzFusion = new Adresse();
            adresseJazzFusion.setNumero_adresse("404");
            adresseJazzFusion.setRue_adresse("Rue du Jazz");
            adresseJazzFusion.setVille_adresse("Lyon");
            adresseJazzFusion.setCode_postal_adresse(69000);
            adresseJazzFusion.setPays_adresse("France");
            adresseJazzFusion.setPublic_adresse(true);
            adresseService.creerAdresse(adresseJazzFusion);

            TypeEvenement typeEvenementJazzFusion = new TypeEvenement();
            typeEvenementJazzFusion.setDescription_type_evenement(TypeEvenementEnum.Boîte);
            typeEvenementService.creerTypeEvenement(typeEvenementJazzFusion);

            StatutEvenement statutEvenementJazzFusion = new StatutEvenement();
            statutEvenementJazzFusion.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementJazzFusion);

            Evenement jazzFusion = new Evenement("JazzFusion", "Un festival de jazz qui mélange les genres et les influences",
                    LocalDate.of(2025, 7, 5), LocalDate.of(2025, 7, 10),
                    45.0f, 900, true);
            jazzFusion.setAdresse(adresseJazzFusion);
            jazzFusion.setTypeEvenement(typeEvenementJazzFusion);
            jazzFusion.setStatutEvenement(statutEvenementJazzFusion);
            jazzFusion.setOrganisateur(utilisateur2);
            evenementService.addEvenement(jazzFusion);
            participeService.addParticipe(new Participe(jazzFusion, utilisateur2));
            participeService.addParticipe(new Participe(jazzFusion, utilisateur4));
            performeService.creer(new Performe(jazzFusion, artiste4));
            performeService.creer(new Performe(jazzFusion, artiste3));

// Ajout de l'événement "FunkGroove"
            Adresse adresseFunkGroove = new Adresse();
            adresseFunkGroove.setNumero_adresse("505");
            adresseFunkGroove.setRue_adresse("Avenue du Funk");
            adresseFunkGroove.setVille_adresse("Bordeaux");
            adresseFunkGroove.setCode_postal_adresse(33000);
            adresseFunkGroove.setPays_adresse("France");
            adresseFunkGroove.setPublic_adresse(true);
            adresseService.creerAdresse(adresseFunkGroove);

            TypeEvenement typeEvenementFunkGroove = new TypeEvenement();
            typeEvenementFunkGroove.setDescription_type_evenement(TypeEvenementEnum.Festival);
            typeEvenementService.creerTypeEvenement(typeEvenementFunkGroove);

            StatutEvenement statutEvenementFunkGroove = new StatutEvenement();
            statutEvenementFunkGroove.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementFunkGroove);

            Evenement funkGroove = new Evenement("FunkGroove", "Plongez dans l'univers groovy du funk et de la soul",
                    LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 7),
                    40.0f, 800, true);
            funkGroove.setAdresse(adresseFunkGroove);
            funkGroove.setTypeEvenement(typeEvenementFunkGroove);
            funkGroove.setStatutEvenement(statutEvenementFunkGroove);
            funkGroove.setOrganisateur(utilisateur6);
            evenementService.addEvenement(funkGroove);
            participeService.addParticipe(new Participe(funkGroove, utilisateur5));
            participeService.addParticipe(new Participe(funkGroove, utilisateur3));
            performeService.creer(new Performe(funkGroove, artiste3));
            performeService.creer(new Performe(funkGroove, artiste1));

// Ajout de l'événement "DiscoFever"
            Adresse adresseDiscoFever = new Adresse();
            adresseDiscoFever.setNumero_adresse("606");
            adresseDiscoFever.setRue_adresse("Rue du Disco");
            adresseDiscoFever.setVille_adresse("Nice");
            adresseDiscoFever.setCode_postal_adresse(06000);
            adresseDiscoFever.setPays_adresse("France");
            adresseDiscoFever.setPublic_adresse(true);
            adresseService.creerAdresse(adresseDiscoFever);

            TypeEvenement typeEvenementDiscoFever = new TypeEvenement();
            typeEvenementDiscoFever.setDescription_type_evenement(TypeEvenementEnum.Festival);
            typeEvenementService.creerTypeEvenement(typeEvenementDiscoFever);

            StatutEvenement statutEvenementDiscoFever = new StatutEvenement();
            statutEvenementDiscoFever.setDescription_statut_evenement("En cours");
            statutEvenementService.creerStatut(statutEvenementDiscoFever);

            Evenement discoFever = new Evenement("DiscoFever", "Une fièvre disco pour revivre les années folles",
                    LocalDate.of(2025, 9, 15), LocalDate.of(2025, 9, 20),
                    35.0f, 700, true);
            discoFever.setAdresse(adresseDiscoFever);
            discoFever.setTypeEvenement(typeEvenementDiscoFever);
            discoFever.setStatutEvenement(statutEvenementDiscoFever);
            discoFever.setOrganisateur(utilisateur1);
            evenementService.addEvenement(discoFever);
            participeService.addParticipe(new Participe(discoFever, utilisateur2));
            participeService.addParticipe(new Participe(discoFever, utilisateur4));
            performeService.creer(new Performe(discoFever, artiste4));
            performeService.creer(new Performe(discoFever, artiste2));




            noterService.addNoter(new Noter(1L, utilisateur1, technoFest, 5, "Super festival, j'ai adoré la fin!"));
            noterService.addNoter(new Noter(2L, utilisateur2, technoFest, 1, "Je n'ai pas du tout apprecié le festival"));
            noterService.addNoter(new Noter(3L, utilisateur3, technoFest, 2, "Festival correct bien que un peu simple"));
            noterService.addNoter(new Noter(4L, utilisateur4, technoFest, 3, "J'ai apprecié l'événement, je recommanderais"));


        };
    }
}
