package com.example.weathertracker.service;

import com.example.weathertracker.store.entity.UserEntity;
import com.example.weathertracker.store.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void saveUser(String login, String password){
        UserEntity user = new UserEntity(login, password);
        userRepo.save(user);
    }

}
