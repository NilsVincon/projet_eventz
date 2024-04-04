package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Adresse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class AdresseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void creerAdresse(Adresse adresse) throws DAOException {
        try {
            entityManager.persist(adresse);
        } catch (Exception e) {
            throw new DAOException("Erreur lors de la création de l'adresse", e);
        }
    }

    public void supprimerAdresse(Adresse adresse) throws DAOException {
        try {
            Adresse adresseToRemove = entityManager.find(Adresse.class, adresse.getId_adresse());
            entityManager.remove(adresseToRemove);
        } catch (Exception e) {
            throw new DAOException("Erreur lors de la suppression de l'adresse", e);
        }
    }

    public Adresse trouverAdresseAvecId(int adresseId) throws DAOException {
        try {
            return entityManager.find(Adresse.class, adresseId);
        } catch (Exception e) {
            throw new DAOException("Erreur lors de la recherche de l'adresse", e);
        }
    }

    public List<Adresse> trouverToutesAdresses() throws DAOException {
        try {
            TypedQuery<Adresse> query = entityManager.createQuery("SELECT a FROM Adresse a", Adresse.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Erreur lors de la recherche de toutes les adresses", e);
        }
    }

    public int compterAdresses() throws DAOException {
        try {
            TypedQuery<Integer> query = entityManager.createQuery("SELECT COUNT(a) FROM Adresse a", int.class);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Erreur lors du comptage des adresses", e);
        }
    }

    public void modifierAdresse(Adresse adresse) throws DAOException {
        try {
            entityManager.merge(adresse);
        } catch (Exception e) {
            throw new DAOException("Erreur lors de la modification de l'adresse", e);
        }
    }
}
