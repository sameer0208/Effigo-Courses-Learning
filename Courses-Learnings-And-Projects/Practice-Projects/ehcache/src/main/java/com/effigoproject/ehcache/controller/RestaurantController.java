package com.effigoproject.ehcache.controller;

import com.effigoproject.ehcache.entity.Restaurant;
import com.effigoproject.ehcache.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRestaurantById(id));
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return service.getAllRestaurants();
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(service.saveRestaurant(restaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        service.deleteRestaurant(id);
        return ResponseEntity.ok("Restaurant deleted successfully!");
    }
}