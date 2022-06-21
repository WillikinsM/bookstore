package com.will.bookstoreapi.auth.registration;


import com.will.bookstoreapi.auth.appUser.AppUser;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@CrossOrigin("*")
public class RegistrationController {

    private final RegistrationService registrationService;


    @PostMapping
    public String register(@RequestBody RegistrationRequest request){

        return registrationService.register(request);
    }



    @GetMapping(path = "user/{id}")
    public ResponseEntity<Collection<? extends GrantedAuthority>> user(@PathVariable Long id) {
        AppUser user = registrationService.getById(id);
        System.out.println(user.getAuthorities());
        return  ResponseEntity.ok().body(user.getAuthorities());
    }


}
