package com.epf.eventz.servlet;

import com.epf.eventz.dao.UtilisateurDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.UtilisateurService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UtilisateurListServlet extends HttpServlet {

    private UtilisateurService utilisateurService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.utilisateurService = new UtilisateurService(new UtilisateurDAO());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Utilisateur> utilisateurs = utilisateurService.findAllUtilisateurs();
            request.setAttribute("utilisateurs", utilisateurs);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/users/list.jsp");
            // ça dépend oú on le met il faut revoir quand on crée le html

            dispatcher.forward(request, response);
        } catch (DAOException e) {
            throw new ServletException("Error listing utilisateurs", e);
        }
    }
}
