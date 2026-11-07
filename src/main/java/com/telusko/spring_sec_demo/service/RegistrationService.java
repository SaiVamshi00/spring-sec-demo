package com.telusko.spring_sec_demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.telusko.spring_sec_demo.dto.RequestRegister;
import com.telusko.spring_sec_demo.entity.LoginEntity;
import com.telusko.spring_sec_demo.repo.UserRepository;

/**
 * RegistrationService
 */
@Service 
public class RegistrationService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public RegistrationService(UserRepository repo, PasswordEncoder encoder)
    {
        this.repo = repo;
        this.encoder = encoder;
    }

    public void saveDataIntoDb(RequestRegister request) {
        
            if(repo.findByUserName(request.username()).isPresent())
            {
                throw new IllegalArgumentException("Username Already Taken");
            }

            LoginEntity user = new LoginEntity(request.username(),encoder.encode(request.password()),"USER");
            repo.save(user);
        
    }




}
