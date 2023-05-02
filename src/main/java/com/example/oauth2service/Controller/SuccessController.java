package com.example.oauth2service.Controller;

import com.example.oauth2service.DTO.UserDTO;
import com.example.oauth2service.Enums.Role;
import com.example.oauth2service.Model.User;
import com.example.oauth2service.Service.UserServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class SuccessController {

    private final UserServices userServices;

    @GetMapping
    public String successfulLogin(OAuth2AuthenticationToken auth2AuthenticationToken){
        log.info("User's Info ---> "+auth2AuthenticationToken.getPrincipal());
        UserDTO user =  UserDTO.builder()
                .username(auth2AuthenticationToken.getPrincipal().getAttribute("email"))
                .role("CLIENT")
                .build();
        User savedUser = userServices.saveUser(user);
        log.info("Saved Oauth2 user's info ---> "+ savedUser);
        return "Successful login!";
    }
}
