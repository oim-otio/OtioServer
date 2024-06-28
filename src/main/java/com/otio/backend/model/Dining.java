package com.otio.backend.model;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.time.LocalTime;


public class Dining extends Activity {
    // Structure: Entity type (abbreviation) + Record number
    // Example: DNN02

    /*
    String id;
    String name;
    String subcategory;
    String imageName;
    double rating;
    String imagePath; // After Spring Web
    String mapsLink;
    List<LocalTime> timeSlots;
    */

    //
        /*
        "id": 1,
        "name": "Android",
        "image": "android.jpeg",
        "rating": "4",
        "imagepath": "http://0:0:..."
        */
    public Dining() {}
    
    public Dining(String id, String name, String subcategory, String imageName, double rating, String imagePath,
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

    public Dining(String id, String name, String subcategory, String imageName, double rating, String imagePath,
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
    public String getSubcategory() {
        return subcategory;
    }

    @Override
    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
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
        return "Dining [id=" + id + ", name=" + name + ", subcategory=" + subcategory + ", imageName=" + imageName
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
        if (obj instanceof Dining) {
            return (this.getId().equals(((Dining)obj).getId()));
        }
        return false;
    }

}
