package com.epf.eventz.service;


import java.util.List;

import com.epf.eventz.dao.PerformeDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;

import com.epf.eventz.model.Performe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformeService {
    @Autowired
    PerformeDAO performeDAO;

    public void creer(Performe performe){
        performeDAO.save(performe);
    }

}
