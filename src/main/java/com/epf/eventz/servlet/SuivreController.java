package com.epf.eventz.servlet;

import com.epf.eventz.model.Artiste;
import com.epf.eventz.service.SuivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SuivreController {
    private final SuivreService suivreService;
    @Autowired
    public SuivreController(SuivreService suivreService) {
        this.suivreService = suivreService;
    }
}
