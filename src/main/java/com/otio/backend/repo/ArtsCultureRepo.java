package com.otio.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.otio.backend.model.ArtsCulture;
import java.util.List;


public interface ArtsCultureRepo extends MongoRepository<ArtsCulture, String> {
    public List<ArtsCulture> findByName(String name);
    public List<ArtsCulture> findBySubcategory(String subcategory);
    public List<ArtsCulture> findByRatingGreaterThanEqual(double rating);
}
