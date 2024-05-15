package com.epf.eventz.servlet;

import com.epf.eventz.model.*;
import com.epf.eventz.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/eventz")
public class HomeController {
    private final EvenementService evenementService;

    @Autowired
    public HomeController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping("/home")
    public String listEvenements(Model model){
        try {
            List<Evenement> evenements = evenementService.findAllEvenements();
            model.addAttribute("evenements", evenements);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "home";
    }
}
