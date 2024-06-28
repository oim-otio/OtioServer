package com.otio.backend.controller;

import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otio.backend.exception.UserException;
import com.otio.backend.model.Activity;
import com.otio.backend.model.User;
import com.otio.backend.repo.ActivityRepo;
import com.otio.backend.repo.ArtsCultureRepo;
import com.otio.backend.repo.DiningRepo;
import com.otio.backend.repo.EntertainmentRepo;
import com.otio.backend.repo.NightlifeRepo;
import com.otio.backend.repo.OutdoorRepo;
import com.otio.backend.repo.UserRepo;
import com.otio.backend.repo.WellnessRepo;


@RestController
@RequestMapping("/activities")
public class ActivitySearchController {

    @Autowired
    ActivityRepo activityRepo;

    @Autowired
    DiningRepo diningRepo;

    @Autowired
    ArtsCultureRepo artsCultureRepo;

    @Autowired
    EntertainmentRepo entertainmentRepo;

    @Autowired
    NightlifeRepo nightlifeRepo;

    @Autowired
    WellnessRepo wellnessRepo;

    @Autowired
    OutdoorRepo outdoorRepo;

    @Autowired
    UserRepo userRepo;

    

    // Search by name
    // Header + Param
    @PostMapping("/search/fullname")
    public PayloadOutput<List<Activity>> getActivitiesByFullName(@RequestParam("fullname") String fullname, @RequestHeader("token") String token) {
        List<Activity> foundActivities = new ArrayList<>();
        try {
            fullname = URLDecoder.decode(fullname, "UTF-8");
            
            User currentUser = userRepo.findByTokenString(token);

            if ((!Objects.isNull(currentUser)) && (!Objects.isNull(foundActivities))) {
                foundActivities = activityRepo.findByName(fullname);
            }
        }
        catch (Exception e) {
            throw new UserException("Invalid parameter.");
        }
        

        return new PayloadOutput<>(LocalDateTime.now(), "OK", foundActivities);
    }

    // Header + Param
    @PostMapping("/search/name")
    public PayloadOutput<List<Activity>> getActivitiesByName(@RequestParam("name") String name, @RequestHeader("token") String token) {
        List<Activity> foundActivities = new ArrayList<>();
        User currentUser = userRepo.findByTokenString(token);

        if (!Objects.isNull(currentUser)) {
            foundActivities = activityRepo.findByNameContainsIgnoreCase(name);
        }

        return new PayloadOutput<>(LocalDateTime.now(), "OK", foundActivities);
    }
    
    // Search by subcategory
    // Header + Param
    @PostMapping("/search/subcategory")
    public PayloadOutput<List<Activity>> getActivitiesBySubcategory(@RequestParam("subcategory") String subcategory, @RequestHeader("token") String token) {
        List<Activity> foundActivities = new ArrayList<>();
        User currentUser = userRepo.findByTokenString(token);

        if (!Objects.isNull(currentUser)) {
            foundActivities = activityRepo.findBySubcategory(subcategory);
        }

        return new PayloadOutput<>(LocalDateTime.now(), "OK", foundActivities);
    }

    // Search by rating greater than equal
    // Header + Param
    @PostMapping("/search/rating")
    public PayloadOutput<List<Activity>> getActivitiesByRating(@RequestParam("rating") double rating, @RequestHeader("token") String token) {
        List<Activity> foundActivities = new ArrayList<>();
        User currentUser = userRepo.findByTokenString(token);

        if (!Objects.isNull(currentUser)) {
            foundActivities = activityRepo.findByRatingGreaterThanEqual(rating);
        }

        return new PayloadOutput<>(LocalDateTime.now(), "OK", foundActivities);
    }
}
