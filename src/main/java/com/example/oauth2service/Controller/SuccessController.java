package com.example.oauth2service.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/")
public class SuccessController {

    @GetMapping
    public String successfulLogin(OAuth2AuthenticationToken auth2AuthenticationToken){
        log.info("User's Info ---> "+auth2AuthenticationToken.getPrincipal());
        return "Successful login!";
    }
}
