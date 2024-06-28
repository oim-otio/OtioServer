package com.otio.backend.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.otio.backend.exception.UserException;
import com.otio.backend.model.Token;
import com.otio.backend.model.User;
import com.otio.backend.repo.UserRepo;

import jakarta.annotation.PreDestroy;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    // Find a user
    public User getUserByToken(String tokenStr) {
        User userFound = userRepo.findByTokenString(tokenStr);
        return userFound;
    }

    // register
    public void registerUser(User user) throws Exception {
        try {
            user.setToken(null);
            userRepo.insert(user);
        }
        catch (DuplicateKeyException d) {
            throw new UserException("This username is already assigned.");
        }
        catch (Exception e) {
            throw e;
        }
    }

    // login
    public User userLogin(User user) throws Exception {
        User foundUser = userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (Objects.isNull(foundUser)) {
            throw new UserException("Try entering the correct username and password.");
        }
        else if (isUserLoggedIn(foundUser)) {
            throw new UserException("The user is already logged in.");
        }

        String tokenText = new ObjectId().toString();
        Token token = new Token(tokenText, LocalDateTime.now().plusDays(100));
        foundUser.setToken(token);
        updateUser(foundUser);

        return foundUser;
    }

    // logout
    public void userLogout(String tokenString) {
        User userFound = userRepo.findByTokenString(tokenString);
        
        if (Objects.isNull(userFound)) {
            throw new UserException("The session is incorrect.");
        }

        if (Objects.isNull(userFound.getToken())) {
            throw new UserException("There is no such session.");
        }

        userFound.setToken(null);
        userRepo.save(userFound);
    }

    // delete user
    public void deleteUser(String tokenString) {
        User userFound = userRepo.findByTokenString(tokenString);
        if (Objects.isNull(userFound)) {
            throw new UserException("There is no such user.");
        }
        
        userRepo.delete(userFound);
    }


    public void updateUser(User user) {
        if (!Objects.isNull(userRepo.findByUsername(user.getUsername()))) {
            userRepo.save(user);
        }
    }
    
    private boolean isUserLoggedIn(User user) {
        return (!Objects.isNull(user.getToken()));
    }

    @PreDestroy
    public void destroyAllSessions() {
        userRepo.findAll().forEach(user -> {
            if (isUserLoggedIn(user)) {
                userLogout(user.getToken().getToken());
            }
        });
    }
}
