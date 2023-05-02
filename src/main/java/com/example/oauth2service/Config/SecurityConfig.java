package com.example.oauth2service.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private String CLIENT_ID = "72913889155-vqs6ltlovjkhughlth06s43f28ruofbh.apps.googleusercontent.com";
    private String CLIENT_SECRET = "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";

    //SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorize->authorize.anyRequest().authenticated())
                .oauth2Login(withDefaults())
                .build();
    }

    //ClientRegistration
    @Bean
    public ClientRegistration googleClientRegistration(){
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .build();
    }
    //ClientRepository--Registered Client's Details i.e Google in this scenario
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        return new InMemoryClientRegistrationRepository(googleClientRegistration());
    }

    //Oauth2ClientRegistrationRepository
    @Bean
    public OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository(OAuth2AuthorizedClientService oAuth2AuthorizedClientService){
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(oAuth2AuthorizedClientService);
    }
    //Oauth2AuthorizedClientService
    @Bean
    public OAuth2AuthorizedClientService oAuth2AuthorizedClientService(ClientRegistrationRepository clientRegistrationRepository){
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }


}
