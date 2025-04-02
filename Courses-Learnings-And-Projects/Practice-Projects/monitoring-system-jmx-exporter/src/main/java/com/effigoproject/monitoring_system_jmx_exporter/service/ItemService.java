package com.effigoproject.monitoring_system_jmx_exporter.service;

import com.effigoproject.monitoring_system_jmx_exporter.entity.Item;
import com.effigoproject.monitoring_system_jmx_exporter.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    public ItemService(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }
}
