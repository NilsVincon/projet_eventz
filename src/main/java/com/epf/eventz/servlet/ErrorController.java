package com.epf.eventz.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/disconnected")
    public String disconnectedError() {
        return "notconnected";
    }

    @GetMapping("/tokenisnotcorrect")
    public String tokenisnotcorrectError() {
        return "tokenisnotcorrect";
    }


}