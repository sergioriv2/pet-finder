package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;
import java.util.List;

@Data
@Document
public class Pet {
    @Id
    private String id;
    private String type;
    private String name;
    private String breed;
    private String description;
    private String weight;
    private String weighMeasurement;
    private List<String> photos;
    @CreatedDate
    @Field(targetType = FieldType.DATE_TIME)
    private Date createdAt;
    @LastModifiedDate
    @Field(targetType = FieldType.DATE_TIME)
    private Date updatedAt;
    public Pet(String type, String name, String breed, String description, String weight, String weighMeasurement, List<String> photos) {
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.description = description;
        this.weight = weight;
        this.weighMeasurement = weighMeasurement;
        this.photos = photos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeighMeasurement() {
        return weighMeasurement;
    }

    public void setWeighMeasurement(String weighMeasurement) {
        this.weighMeasurement = weighMeasurement;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
