package com.otio.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import com.otio.backend.model.Outdoor;

public interface OutdoorRepo extends MongoRepository<Outdoor, String>{
    public List<Outdoor> findByName(String name);
    public List<Outdoor> findBySubcategory(String subcategory);
    public List<Outdoor> findByRatingGreaterThanEqual(double rating);
}