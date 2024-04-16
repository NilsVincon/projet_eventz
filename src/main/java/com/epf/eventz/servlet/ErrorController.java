package com.epf.eventz.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // Handle the error here, e.g., return the name of an error page
        return "error"; // Assuming you have an "error.html" template
    }
}