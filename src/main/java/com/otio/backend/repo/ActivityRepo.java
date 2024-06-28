package com.otio.backend.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.otio.backend.model.Activity;

public interface ActivityRepo extends MongoRepository<Activity, String> {
    public List<Activity> findByName(String name);
    public List<Activity> findByNameContainsIgnoreCase(String name);
    public List<Activity> findBySubcategory(String subcategory);
    public List<Activity> findByRatingGreaterThanEqual(double rating);
}
