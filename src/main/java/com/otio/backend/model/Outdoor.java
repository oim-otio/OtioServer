package com.otio.backend.model;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.time.LocalTime;

public class Outdoor extends Activity {

    public Outdoor() {}

    public Outdoor(String id, String name, String subcategory, String imageName, double rating, String imagePath,
            String mapsLink) {
        this.id = id;
        this.name = name;
        this.subcategory = subcategory;
        this.imageName = imageName;
        this.rating = rating;
        this.imagePath = imagePath;
        this.mapsLink = mapsLink;
        this.timeSlots = new ArrayList<LocalTime>();
    }

    public Outdoor(String id, String name, String subcategory, String imageName, double rating, String imagePath,
            String mapsLink, List<LocalTime> timeSlots) {
        this.id = id;
        this.name = name;
        this.subcategory = subcategory;
        this.imageName = imageName;
        this.rating = rating;
        this.imagePath = imagePath;
        this.mapsLink = mapsLink;
        this.timeSlots = timeSlots;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSubcategory() {
        return subcategory;
    }

    @Override
    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    @Override
    public String getImageName() {
        return imageName;
    }

    @Override
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String getMapsLink() {
        return mapsLink;
    }

    @Override
    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

    @Override
    public List<LocalTime> getTimeSlots() {
        return timeSlots;
    }

    @Override
    public void setTimeSlots(List<LocalTime> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @Override
    public String toString() {
        return "Outdoor [id=" + id + ", name=" + name + ", subcategory=" + subcategory + ", imageName=" + imageName
                + ", rating=" + rating + ", imagePath=" + imagePath + ", mapsLink=" + mapsLink + ", timeSlots="
                + timeSlots + "]";
    }
    
    @Override
    public void addTimeSlot(LocalTime timeSlot) {
        if (Objects.isNull(this.timeSlots)) {
            this.timeSlots = new ArrayList<LocalTime>();
        }

        if (!timeSlots.contains(timeSlot)) {
            timeSlots.add(timeSlot);
        }
    }

    @Override
    public void removeTimeSlot(LocalTime timeSlot) {
        if ((!Objects.isNull(timeSlot)) && (timeSlots.contains(timeSlot))) {
            timeSlots.remove(timeSlot);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Outdoor) {
            return (this.getId().equals(((Outdoor)obj).getId()));
        }
        return false;
    }
}
