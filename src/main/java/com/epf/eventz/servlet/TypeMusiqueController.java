package com.epf.eventz.servlet;

import com.epf.eventz.model.Adresse;
import com.epf.eventz.model.TypeMusique;
import com.epf.eventz.service.AdresseService;
import com.epf.eventz.service.TypeMusiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TypeMusiqueController {

    private final TypeMusiqueService typeMusiqueService;

    @Autowired
    public TypeMusiqueController(TypeMusiqueService typeMusiqueService) {
        this.typeMusiqueService = typeMusiqueService;
    }

    @GetMapping("/listetypemusique")
    public String listTypeMusique(Model model){
        try {
            List<TypeMusique> typeMusiques = typeMusiqueService.findAllTypeMusiques()   ;
            model.addAttribute("typemusiques", typeMusiques);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "listetypemusique";
    }
}

