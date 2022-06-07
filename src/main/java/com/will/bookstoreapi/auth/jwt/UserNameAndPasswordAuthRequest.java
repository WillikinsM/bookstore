package com.will.bookstoreapi.auth.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserNameAndPasswordAuthRequest {
    private String username;
    private String password;
}
