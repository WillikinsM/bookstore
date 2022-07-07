package com.will.bookstoreapi.auth.email;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.email")
@NoArgsConstructor
@Getter
@Setter
public class EmailConfig {
    private String secretEmail;

}
