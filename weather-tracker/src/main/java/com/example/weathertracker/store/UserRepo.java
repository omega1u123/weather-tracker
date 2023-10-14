package com.example.weathertracker.store;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    public UserEntity findUserEntitiesByLogin(String login);
}
