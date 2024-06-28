package com.otio.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.otio.backend.model.Nightlife;
import java.util.List;
public interface NightlifeRepo extends MongoRepository<Nightlife, String>{
    public List<Nightlife> findByName(String name);
    public List<Nightlife> findBySubcategory(String subcategory);
    public List<Nightlife> findByRatingGreaterThanEqual(double rating);
}