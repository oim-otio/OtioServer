package com.otio.backend.model;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private String username;

    private String name;
    private String lastname;
    private String password;
    
    private String ppPath;

    private Token token;

  
    List<List<LocalTime>> availableTimeslots;

    // Saved items
    List<SavedActivity> savedActivities;

    public User() {}

    public User(String username, String name, String lastname, String password) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.availableTimeslots = new ArrayList<List<LocalTime>>();
        availableTimeslots.add(new ArrayList<>());
        this.savedActivities = new ArrayList<>();
    }

    public User(String username, String name, String lastname, String password, Token token, String ppPath) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.token = token;
        this.ppPath = ppPath;
        this.availableTimeslots = new ArrayList<List<LocalTime>>();
        availableTimeslots.add(new ArrayList<>());
        this.savedActivities = new ArrayList<>();
    }

    public User(String username, String name, String lastname, String ppPath, List<List<LocalTime>> availableTimeslots,
            List<SavedActivity> savedActivities) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.ppPath = ppPath;
        this.availableTimeslots = availableTimeslots;
        this.savedActivities = savedActivities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getPpPath() {
        return ppPath;
    }

    public void setPpPath(String ppPath) {
        this.ppPath = ppPath;
    }

    public List<List<LocalTime>> getAvailableTimeslots() {
        return availableTimeslots;
    }

    public void setAvailableTimeslots(List<List<LocalTime>> availableTimeslots) {
        this.availableTimeslots = availableTimeslots;
    }

    public void addAvailableTimeslot(List<LocalTime> timeslotToAdd) {
        if (Objects.isNull(this.availableTimeslots)) {
            this.availableTimeslots = new ArrayList<List<LocalTime>>();
        }

        if (!availableTimeslots.contains(timeslotToAdd)) {
            availableTimeslots.add(timeslotToAdd);
        }
    }

    public void deleteTimeslot(List<LocalTime> timeslotToRemove){
        if (availableTimeslots.contains(timeslotToRemove)) {
            availableTimeslots.remove(timeslotToRemove);
        }
    }

    public SavedActivity findSavedActivity(String id) {
        if ((!Objects.isNull(savedActivities)) && (!savedActivities.isEmpty())) {
            for (SavedActivity savedActivity: savedActivities) {
                if (savedActivity.getId().equals(id)) {
                    return savedActivity;
                }
            }
        }

        return null;
    }

    public List<SavedActivity> getSavedActivities() {
        return savedActivities;
    }

    public void setSavedActivities(List<SavedActivity> savedActivities) {
        this.savedActivities = savedActivities;
    }

    public void saveNewActivity(SavedActivity activityToSave) {
        if (Objects.isNull(this.savedActivities)) {
            this.savedActivities = new ArrayList<SavedActivity>();
        }

        if (!savedActivities.contains(activityToSave)) {
            savedActivities.add(activityToSave);
        }
    }

    public void deleteSavedActivity(SavedActivity activityToDelete) {
        if (savedActivities.contains(activityToDelete)) {
            savedActivities.remove(activityToDelete);
        }
    }

    public boolean isComplete() {
        return ((!Objects.isNull(name) && (!Objects.isNull(lastname)) && (!Objects.isNull(username)) && (!Objects.isNull(password))) && 
        ((!name.equals(null)) && (!lastname.equals(null)) && (!username.equals(null)) && (!password.equals(null))));
    }
}
