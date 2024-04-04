package com.epf.eventz;

import com.epf.eventz.model.Adresse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AdresseServiceIntegrationTest {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            // Création de l'EntityManagerFactory à partir de l'unité de persistance spécifiée dans le persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("eventz_persistence");

            // Création de l'EntityManager
            entityManager = entityManagerFactory.createEntityManager();

            // Début de la transaction
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Création d'une nouvelle entité Adresse
            Adresse adresse = new Adresse("2bis", "rue d'alésia", "Paris", 75014, "France", true);

            // Persister l'entité dans la base de données
            entityManager.persist(adresse);

            // Validation de la transaction
            transaction.commit();

            System.out.println("Adresse créée avec succès. ID: " + adresse.getId_adresse());
        } catch (Exception e) {
            // En cas d'erreur, annuler la transaction
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Fermeture de l'EntityManager et de l'EntityManagerFactory
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}