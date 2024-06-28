package com.otio.backend.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otio.backend.exception.UserException;
import com.otio.backend.model.SavedActivity;
import com.otio.backend.model.Token;
import com.otio.backend.model.User;
import com.otio.backend.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
    //function to get username and password
    // Signing up ==> insert query

    @Autowired
    private UserService userService;

    // Register method that uses the insert query
  
    @PostMapping("/register")
    public PayloadOutput<String> registerUser(@RequestBody User user) throws UserException {
        if ((Objects.isNull(user)) || (!user.isComplete())) {
            throw new UserException("All fields must be fulfilled.");
        }

        try {
            userService.registerUser(user);
        }
        catch (Exception e) {
            throw new UserException(e.getMessage());
        }

        return new PayloadOutput<String>(LocalDateTime.now(), "OK", "User is registered.");
    }

    // login
    @PostMapping("/login")
    public PayloadOutput<Token> userLogin(@RequestBody User user) {
        try {
            user = userService.userLogin(user);
        }
        catch (Exception e) {
            throw new UserException(e.getMessage());
        }

        return new PayloadOutput<Token>(LocalDateTime.now(), "OK", user.getToken());
        
    }

    // logout
    @GetMapping("/logout")
    public PayloadOutput<String> userLogout(@RequestHeader("token") String tokenString) {
        if (tokenString.equals(null)) {
            throw new UserException("There is no session.");
        }

        userService.userLogout(tokenString);

        return new PayloadOutput<String>(LocalDateTime.now(), "OK", "User session is destroyed.");
    }

    // delete user
    @GetMapping("/deleteaccount")
    public PayloadOutput<String> deleteUser(@RequestHeader("token") String tokenString) {
        userService.deleteUser(tokenString);

        return new PayloadOutput<String>(LocalDateTime.now(), "OK", "The account is destroyed.");
    }

    // Function to set available timeslots
    @PostMapping("/addtimeslot")
    public PayloadOutput<List<LocalTime>> addTimeslot(@RequestHeader("token") String tokenString, @RequestBody List<LocalTime> timeslotToAdd) {
        User currentUser = userService.getUserByToken(tokenString);

        if ((!Objects.isNull(currentUser)) && (!Objects.isNull(timeslotToAdd)) && (timeslotToAdd.size() == 2)) {
            currentUser.addAvailableTimeslot(timeslotToAdd);
            userService.updateUser(currentUser);

            return new PayloadOutput<List<LocalTime>>(LocalDateTime.now(), "OK", timeslotToAdd);
        }

        return new PayloadOutput<List<LocalTime>>(LocalDateTime.now(), "ERROR", null);
    }

    @PostMapping("/removetimeslot")
    public PayloadOutput<List<LocalTime>> removeTimeslot(@RequestHeader("token") String tokenString, @RequestBody List<LocalTime> timeslotToRemove) {
        User currentUser = userService.getUserByToken(tokenString);

        if ((!Objects.isNull(currentUser)) && (!Objects.isNull(currentUser.getAvailableTimeslots())) && (currentUser.getAvailableTimeslots().contains(timeslotToRemove))
            && (!Objects.isNull(timeslotToRemove)) && (timeslotToRemove.size() == 2)) {
            currentUser.deleteTimeslot(timeslotToRemove);
            userService.updateUser(currentUser);

            return new PayloadOutput<List<LocalTime>>(LocalDateTime.now(), "OK", timeslotToRemove);
        }

        return new PayloadOutput<List<LocalTime>>(LocalDateTime.now(), "ERROR", null);
    }

    @GetMapping("/gettimeslots")
    public PayloadOutput<List<List<LocalTime>>> getTimeslots(@RequestHeader("token") String tokenString) {
        User currentUser = userService.getUserByToken(tokenString);

        if (!Objects.isNull(currentUser)) {
            return new PayloadOutput<List<List<LocalTime>>>(LocalDateTime.now(), "OK", currentUser.getAvailableTimeslots());
        }

        return new PayloadOutput<List<List<LocalTime>>>(LocalDateTime.now(), "ERROR", null);
    }

    // Function for getting the saved items
    @GetMapping("/savedactivities")
    public PayloadOutput<List<SavedActivity>> getSavedActivities(@RequestHeader("token") String tokenString) {
        User currentUser = userService.getUserByToken(tokenString);

        if (!Objects.isNull(currentUser)) {
            return new PayloadOutput<List<SavedActivity>>(LocalDateTime.now(), "OK", currentUser.getSavedActivities());
        }

        return new PayloadOutput<List<SavedActivity>>(LocalDateTime.now(), "ERROR", null);
    }

    // Function for saving a new item
    @PostMapping("/saveactivity")
    public PayloadOutput<SavedActivity> saveNewActivity(@RequestHeader("token") String tokenString, @RequestBody SavedActivity activity) {
        User currentUser = userService.getUserByToken(tokenString);

        if ((!Objects.isNull(currentUser)) && (!Objects.isNull(activity))) {
            currentUser.saveNewActivity(activity);
            userService.updateUser(currentUser);

            return new PayloadOutput<SavedActivity>(LocalDateTime.now(), "OK", activity);
        }

        return new PayloadOutput<SavedActivity>(LocalDateTime.now(), "ERROR", null);
    }

    // Header + Param
    @PostMapping("/deletesavedactivity")
    public PayloadOutput<String> deleteSavedActivity(@RequestHeader("token") String tokenString, @RequestParam("activity_id") String activityId) {
        User currentUser = userService.getUserByToken(tokenString);

        if (!Objects.isNull(currentUser)) {
            if (!Objects.isNull(activityId)) {
                SavedActivity foundActivity = currentUser.findSavedActivity(activityId);
                if (!Objects.isNull(foundActivity)) {
                    currentUser.deleteSavedActivity(foundActivity);
                    userService.updateUser(currentUser);
                    return new PayloadOutput<String>(LocalDateTime.now(), "OK", "Activity is deleted.");
                }
            }
            return new PayloadOutput<String>(LocalDateTime.now(), "ERROR", "The user did not save such an activity.");
        }

        return new PayloadOutput<String>(LocalDateTime.now(), "ERROR", "Please log in. We could not detect your session attempt.");
    }

    @GetMapping("/profilepicture")
    public PayloadOutput<String> retrieveProfilePicture(@RequestHeader("token") String tokenString) {
        User currentUser = userService.getUserByToken(tokenString);
        
        if (!Objects.isNull(currentUser)) {
            if (!Objects.isNull(currentUser.getPpPath())) {
                return new PayloadOutput<String>(LocalDateTime.now(), "OK", currentUser.getPpPath());
            }
            else {
                throw new UserException("The user does not have a profile picture.");
            }
        }

        return new PayloadOutput<String>(LocalDateTime.now(), "ERROR", "Please log in. We could not detect your session attempt.");
    }

    @PostMapping("/profilepicture/update")
    public PayloadOutput<String> updateProfilePicture(@RequestHeader("token") String tokenString, @RequestBody String newPpPath) {
        User currentUser = userService.getUserByToken(tokenString);
        
        if ((!Objects.isNull(currentUser)) && (!Objects.isNull(newPpPath))) {
            currentUser.setPpPath(newPpPath);
            userService.updateUser(currentUser);
            return new PayloadOutput<String>(LocalDateTime.now(), "OK", "Profile picture is successfully updated.");
        }
        else if (Objects.isNull(currentUser)) {
            return new PayloadOutput<String>(LocalDateTime.now(), "ERROR", "Please log in. We could not detect your session attempt.");
        }

        return new PayloadOutput<String>(LocalDateTime.now(), "ERROR", "Profile picture could not be updated.");
    }

    @GetMapping("/profilepicture/delete")
    public PayloadOutput<String> deleteProfilePicture(@RequestHeader("token") String tokenString) {
        User currentUser = userService.getUserByToken(tokenString);
        
        if (!Objects.isNull(currentUser)) {
            if (Objects.isNull(currentUser.getPpPath())) {
                return new PayloadOutput<String>(LocalDateTime.now(), "ERROR", "The user does not have a profile picture.");
            }
            currentUser.setPpPath(null);
            userService.updateUser(currentUser);
            return new PayloadOutput<String>(LocalDateTime.now(), "OK", "Profile picture is successfully deleted.");
        }

        return new PayloadOutput<String>(LocalDateTime.now(), "ERROR", "Please log in. We could not detect your session attempt.");
    }
}
