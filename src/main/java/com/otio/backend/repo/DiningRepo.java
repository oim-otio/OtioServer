package com.otio.backend.repo;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.otio.backend.model.Dining;
import java.util.List;

public interface DiningRepo extends MongoRepository<Dining, String> {
    
    public List<Dining> findByName(String name);
    public List<Dining> findBySubcategory(String subcategory);
    public List<Dining> findByRatingGreaterThanEqual(double rating);

}
