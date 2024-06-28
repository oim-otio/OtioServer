package com.otio.backend.controller;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otio.backend.exception.UserException;
import com.otio.backend.model.User;
import com.otio.backend.repo.UserRepo;


@RestController
@RequestMapping("/users")
public class UserSearchController {
    @Autowired
    UserRepo userRepo;

 
    @GetMapping("/{username}")
    public PayloadOutput<User> getUserFromUsername(@PathVariable("username") String username, @RequestHeader("token") String token) {
        User currentUser = userRepo.findByTokenString(token);

        if (Objects.isNull(currentUser)) {
            throw new UserException("Please log in. We could not detect your session attempt.");
        }

        User foundUser = userRepo.findByUsername(username);
        if (Objects.isNull(foundUser)) {
            return new PayloadOutput<User>(LocalDateTime.now(), "ERROR", foundUser);
        }

        return new PayloadOutput<User>(LocalDateTime.now(), "OK", new User(foundUser.getUsername(), foundUser.getName(), foundUser.getLastname(), foundUser.getPpPath(), foundUser.getAvailableTimeslots(), foundUser.getSavedActivities()));
    }
}
