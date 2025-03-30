package com.effigoproject.multi_level_caching.Controller;

import com.effigoproject.multi_level_caching.entity.Property;
import com.effigoproject.multi_level_caching.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService)
    {
        this.propertyService = propertyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        return ResponseEntity.ok(propertyService.saveProperty(property));
    }
}