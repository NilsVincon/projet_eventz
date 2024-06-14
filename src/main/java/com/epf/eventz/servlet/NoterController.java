package com.epf.eventz.servlet;

import com.epf.eventz.dao.EvenementDAO;
import com.epf.eventz.dao.NoterDAO;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Noter;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.NoterService;
import com.epf.eventz.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class NoterController {

    @Autowired
    private NoterService noterService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private EvenementDAO evenementDao;


    @PostMapping("/submitAvis")
    public String submitAvis(@RequestParam("note") int note, @RequestParam("commentaire") String commentaire, @RequestParam("evenementId") Long evenementId) {
        Evenement evenement = evenementDao.findById(evenementId).orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + evenementId));
        Noter avis = new Noter();
        avis.setNote(note);
        avis.setCommentaire(commentaire);
        avis.setEvenement(evenement);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Utilisateur> utilisateurOptional = null;
        Utilisateur user = null;
        try {
            utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(username);
            if (utilisateurOptional.isPresent()) {
                user = utilisateurOptional.get();
                avis.setUtilisateur(user);

            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        noterService.addNoter(avis);
        return "redirect:/eventz/evenement/details/" + evenement.getIdEvenement(); // Rediriger vers la page souhaitée après soumission
    }
}
