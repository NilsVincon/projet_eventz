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

    public void deleteTypeMusique(TypeMusique typeMusique){
        typeMusiqueDAO.delete(typeMusique);
    }

    public Optional<TypeMusique> findTypeMusiqueAvecId(long idTypeMusique) throws ServiceException {
        return typeMusiqueDAO.findById(idTypeMusique);
    }

    public List<TypeMusique> findAllTypeMusiques() {
        return typeMusiqueDAO.findAll();
    }

    public long compterTypeMusiques() throws ServiceException {
        return typeMusiqueDAO.count();

    }


}
