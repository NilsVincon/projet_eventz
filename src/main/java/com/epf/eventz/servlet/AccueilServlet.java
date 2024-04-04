package com.epf.eventz.servlet;

import com.epf.eventz.exception.ServiceException;

import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class AccueilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UtilisateurService utilisateurService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
