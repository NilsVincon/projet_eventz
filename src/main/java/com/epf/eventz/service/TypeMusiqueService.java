package com.epf.eventz.service;

import com.epf.eventz.dao.TypeMusiqueDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.TypeMusique;
import com.epf.eventz.model.TypeMusique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeMusiqueService {

    private final TypeMusiqueDAO typeMusiqueDAO;

    @Autowired
    public TypeMusiqueService(TypeMusiqueDAO typeMusiqueDAO) {
        this.typeMusiqueDAO = typeMusiqueDAO;
    }


    public void addTypeMusique(TypeMusique typeMusique){
        typeMusiqueDAO.save(typeMusique);
    }

    public void deleteTypeMusique(Long typemusiqueId){
        boolean exists = typeMusiqueDAO.existsById(typemusiqueId);
        if (!exists){
            throw new IllegalStateException(
                    "typemusique id"+typemusiqueId+"existe pas"
            );
        }
        typeMusiqueDAO.deleteById(typemusiqueId);
    }

    public Optional<TypeMusique> findTypeMusiqueAvecId(long idTypeMusique) throws ServiceException {
        return typeMusiqueDAO.findById(idTypeMusique);
    }

    public List<TypeMusique> findAllTypeMusiques() {
        return typeMusiqueDAO.findAll();
    }
    public int compterTypeMusique() throws ServiceException{
        return  (int) typeMusiqueDAO.count();
    }

    public long compterTypeMusiques() throws ServiceException {
        return typeMusiqueDAO.count();

    }

    public void updateTypemusique(Long typemusiqueId, TypeMusique typemusique){
        TypeMusique typemusiqueToUpdate = typeMusiqueDAO.findById(typemusiqueId)
                .orElseThrow(() -> new IllegalStateException("L'typemusique avec l'ID " + typemusiqueId + " n'existe pas"));

        typemusiqueToUpdate.setDescription_type_musique(typemusique.getDescription_type_musique());


        typeMusiqueDAO.save(typemusiqueToUpdate);
    }


}
