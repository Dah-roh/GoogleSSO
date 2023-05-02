package com.example.oauth2service.Service;


import com.example.oauth2service.Model.User;
import com.example.oauth2service.DTO.UserDTO;

public interface UserServices {
    User saveUser(UserDTO user);
}
