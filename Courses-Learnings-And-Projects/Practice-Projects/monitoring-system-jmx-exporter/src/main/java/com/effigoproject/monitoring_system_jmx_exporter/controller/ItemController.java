package com.effigoproject.monitoring_system_jmx_exporter.controller;

import com.effigoproject.monitoring_system_jmx_exporter.entity.Item;
import com.effigoproject.monitoring_system_jmx_exporter.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private ItemService itemService;
    public ItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }
}
