package com.example.weathertracker.store.repository;

import com.example.weathertracker.store.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    public UserEntity findUserEntitiesByLogin(String login);
}
