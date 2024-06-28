package com.otio.backend.model;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class Activity {
    @Id
    String id;
    String name;
    String subcategory;
    String imageName;
    double rating;
    String imagePath; // After Spring Web
    String mapsLink;
    List<LocalTime> timeSlots;

    public abstract void addTimeSlot(LocalTime timeSlot);

    public abstract void removeTimeSlot(LocalTime timeSlot);

    public abstract String getId();

    public abstract void setId(String id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getSubcategory();

    public abstract void setSubcategory(String subcategory);

    public abstract String getImageName();

    public abstract void setImageName(String imageName);

    public abstract double getRating();

    public abstract void setRating(double rating);

    public abstract String getImagePath();

    public abstract void setImagePath(String imagePath);

    public abstract String getMapsLink();

    public abstract void setMapsLink(String mapsLink);

    public abstract List<LocalTime> getTimeSlots();

    public abstract void setTimeSlots(List<LocalTime> timeSlots);

    @Override
    public abstract boolean equals(Object obj);
}
