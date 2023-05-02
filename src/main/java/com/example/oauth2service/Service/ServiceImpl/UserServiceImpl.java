package com.example.oauth2service.Service.ServiceImpl;

import com.example.oauth2service.Enums.Oauth2Provider;
import com.example.oauth2service.Model.User;
import com.example.oauth2service.Repository.UserRepositories;
import com.example.oauth2service.Service.UserServices;
import com.example.oauth2service.DTO.UserDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServices{

    private UserRepositories userRepositories;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepositories userRepositories, PasswordEncoder passwordEncoder){
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User saveUser(UserDTO user){
        try{
            User saveUser = new User(user);
            saveUser.setProvider(Oauth2Provider.GOOGLE);
            //setting a default password just in case and using the
            //set of string that comes before the "@" in the user's email
            //e.g darogadibia@gmail.com, will have darogadibia as the password.
            saveUser.setPassword(passwordEncoder.encode(saveUser.getUsername().split("@")[0]));
            return userRepositories.save(saveUser);
        }
        catch (Exception e){
            throw new RuntimeException("Cannot save user "+ e.getMessage());
        }
    }

}
