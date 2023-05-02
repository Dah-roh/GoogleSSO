package com.example.oauth2service.Config;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Builder
public class Constants {


//TODO: "secret" the secrets
    @Value("${client-id}")
    static String Client_ID;
    @Value("${client-secret}")
    static String Client_Secret;
}
