package com.otio.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import com.otio.backend.model.Wellness;

public interface WellnessRepo extends MongoRepository<Wellness, String> {

    public List<Wellness> findByName(String name);
    public List<Wellness> findBySubcategory(String subcategory);
    public List<Wellness> findByRatingGreaterThanEqual(double rating);

}
