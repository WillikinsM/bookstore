package com.will.bookstoreapi.auth.registration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "api/v1/confirmation")
@ServletComponentScan("")
public class TokenConfirmationController {

    private final RegistrationService registrationService;

    @Autowired
    public TokenConfirmationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping(path = "/")
    public String confirmation(@RequestParam("token") String token) {
        registrationService.confirmToken(token);

        return "confirmation";
    }


}
