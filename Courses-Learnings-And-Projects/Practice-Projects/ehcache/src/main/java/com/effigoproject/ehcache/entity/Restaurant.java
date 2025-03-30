package com.effigoproject.ehcache.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("restaurant_name")
    private String name;

    @JsonProperty("owner_name")
    private String ownerName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("zip_code")
    private String zipCode;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("website")
    private String website;

    @JsonProperty("cuisine_type")
    private String cuisineType;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("opening_hours")
    private String openingHours;

    @JsonProperty("price_range")
    private String priceRange;

    @JsonProperty("delivery_available")
    private Boolean deliveryAvailable;

    @JsonProperty("takeout_available")
    private Boolean takeoutAvailable;

    @JsonProperty("vegetarian_options")
    private Boolean vegetarianOptions;

    @JsonProperty("alcohol_served")
    private Boolean alcoholServed;

    @JsonProperty("parking_available")
    private Boolean parkingAvailable;

    @JsonProperty("capacity")
    private Integer capacity;

    @JsonProperty("year_established")
    private Integer yearEstablished;

    @JsonProperty("specialties")
    private String specialties;

    @JsonProperty("description")
    private String description;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("payment_methods")
    private String paymentMethods;

    @JsonProperty("reservation_required")
    private Boolean reservationRequired;

    @JsonProperty("ambiance")
    private String ambiance;

    @JsonProperty("staff_count")
    private Integer staffCount;

    @JsonProperty("health_score")
    private Double healthScore;

}