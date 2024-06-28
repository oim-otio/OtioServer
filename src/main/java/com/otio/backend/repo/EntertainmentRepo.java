package com.otio.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

import com.otio.backend.model.Entertainment;

public interface EntertainmentRepo extends MongoRepository<Entertainment, String>{
    
    public List<Entertainment> findByName(String name);
    public List<Entertainment> findBySubcategory(String subcategory);
    public List<Entertainment> findByRatingGreaterThanEqual(double rating);
}
