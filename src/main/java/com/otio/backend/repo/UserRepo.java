package com.otio.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.otio.backend.model.User;
import java.util.List;

public interface UserRepo extends MongoRepository<User, String> {
    public User findByUsername(String username);
    public User findByUsernameAndPassword(String username, String password);
    public List<User> findByName(String name);
    public List<User> findByLastname(String lastname);

    @Query("{'token.token':?0}")
    public User findByTokenString(String token);
}
