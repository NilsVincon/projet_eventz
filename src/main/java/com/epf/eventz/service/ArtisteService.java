package com.epf.eventz.service;

import com.epf.eventz.dao.ArtisteDAO;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Artiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtisteService {
    private ArtisteDAO artisteDAO;

    @Autowired
    public ArtisteService(ArtisteDAO artisteDAO) {
        this.artisteDAO = artisteDAO;
    }

    public void addArtiste(Artiste artiste){
        artisteDAO.save(artiste);
        System.out.println(artiste);
    }

    public void deleteArtiste(Long artisteId){
        boolean exists = artisteDAO.existsById(artisteId);
        if (!exists){
            throw new IllegalStateException(
                    "artiste id"+artisteId+"existe pas"
            );
        }
        artisteDAO.deleteById(artisteId);
    }

    public Optional<Artiste> findArtisteById(long idArtiste) throws ServiceException {
        return artisteDAO.findById(idArtiste);
    }

    public List<Artiste> findAllArtistes() {
        return artisteDAO.findAll();
    }

    public int countArtistes() throws ServiceException {
        return (int) artisteDAO.count();
    }

    public void updateArtiste(Long artisteId, Artiste artiste){
        Artiste artisteToUpdate = artisteDAO.findById(artisteId)
                .orElseThrow(() -> new IllegalStateException("L'artiste avec l'ID " + artisteId + " n'existe pas"));

        artisteToUpdate.setNom_artiste(artiste.getNom_artiste());
        artisteToUpdate.setDescription_artiste(artiste.getDescription_artiste());


        artisteDAO.save(artisteToUpdate);
    }


}
