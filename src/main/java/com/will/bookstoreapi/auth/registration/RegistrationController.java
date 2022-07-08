package com.will.bookstoreapi.auth.registration;


import com.will.bookstoreapi.auth.appUser.AppUser;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @GetMapping(path = "user/{email}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Collection<? extends GrantedAuthority>> user(@PathVariable String email) {
        AppUser user = registrationService.getByUserName(email);
        return  ResponseEntity.ok().body(user.getAuthorities());
    }

    @DeleteMapping("user/{email}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email){
        AppUser user = registrationService.getByUserName(email);
        registrationService.deleteUser(user);
        return ResponseEntity.accepted().build();
    }

}
