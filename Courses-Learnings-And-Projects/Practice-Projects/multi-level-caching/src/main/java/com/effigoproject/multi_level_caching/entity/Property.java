package com.effigoproject.multi_level_caching.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("propertyType")
    private String propertyType;

    @JsonProperty("price")
    private double price;

    @JsonProperty("area")
    private double area;

    @JsonProperty("bedrooms")
    private int bedrooms;

    @JsonProperty("bathrooms")
    private int bathrooms;

    @JsonProperty("floors")
    private int floors;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    @JsonProperty("zipCode")
    private String zipCode;

    @JsonProperty("furnished")
    private boolean furnished;

    @JsonProperty("parking")
    private boolean parking;

    @JsonProperty("swimmingPool")
    private boolean swimmingPool;

    @JsonProperty("yearBuilt")
    private int yearBuilt;

    @JsonProperty("available")
    private boolean available;

    @JsonProperty("ownerName")
    private String ownerName;

    @JsonProperty("ownerContact")
    private String ownerContact;

    @JsonProperty("petFriendly")
    private boolean petFriendly;

    @JsonProperty("garden")
    private boolean garden;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("energyRating")
    private String energyRating;

    @JsonProperty("underMortgage")
    private boolean underMortgage;

    @JsonProperty("nearSchool")
    private boolean nearSchool;

    @JsonProperty("nearHospital")
    private boolean nearHospital;

    @JsonProperty("nearMall")
    private boolean nearMall;

    @JsonProperty("facingDirection")
    private String facingDirection;

    @JsonProperty("liftAvailable")
    private boolean liftAvailable;

    @JsonProperty("security24x7")
    private boolean security24x7;

    @JsonProperty("waterSupply")
    private boolean waterSupply;

    @JsonProperty("electricityBackup")
    private boolean electricityBackup;

    @JsonProperty("flooringType")
    private String flooringType;

    @JsonProperty("propertyStatus")
    private String propertyStatus;

    @JsonProperty("gymFacility")
    private boolean gymFacility;

    @JsonProperty("clubHouse")
    private boolean clubHouse;

    @JsonProperty("joggingTrack")
    private boolean joggingTrack;

    @JsonProperty("childrenPlayArea")
    private boolean childrenPlayArea;

    @JsonProperty("legalStatus")
    private String legalStatus;

    @Lob
    @JsonProperty("additionalDetails")
    private String additionalDetails;

}
