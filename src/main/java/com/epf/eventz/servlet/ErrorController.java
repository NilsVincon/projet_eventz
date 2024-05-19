package com.epf.eventz.servlet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @GetMapping("/addfriendimpossible")
    public ResponseEntity<String> addfriendimpossibleError() {
        return new ResponseEntity<>("Add friend is impossible", HttpStatus.BAD_REQUEST);
    }


}