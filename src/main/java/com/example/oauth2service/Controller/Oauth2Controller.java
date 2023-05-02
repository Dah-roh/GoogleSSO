package com.example.oauth2service.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/login")
public class Oauth2Controller {

    @GetMapping("/oauth2/code/google")
    public String oauth2Authentication(){
        return "redirecting user";
    }
}
