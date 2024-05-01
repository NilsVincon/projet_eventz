package com.epf.eventz.servlet;

import com.epf.eventz.model.TypeEvenement;
import com.epf.eventz.service.TypeEvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TypeEvenementController {

    private final TypeEvenementService typeEvenementService;

    @Autowired
    public TypeEvenementController(TypeEvenementService typeEvenementService){ this.typeEvenementService = typeEvenementService;}

    @GetMapping("/listetypeevenement")
    public String listTypeEvenement(Model model) {
        try {
            List<TypeEvenement> typeEvenement = typeEvenementService.trouverTousTypeEvenements();
            model.addAttribute("typeevenements", typeEvenement);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "listetypeevenement";
    }
}

