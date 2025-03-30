package com.effigoproject.transactionproducer.controller;

import com.effigoproject.transactionproducer.entity.MasterItem;
import com.effigoproject.transactionproducer.service.MasterItemProducer;
import com.effigoproject.transactionproducer.service.MasterItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master-items")
public class MasterItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterItemController.class);

    private final MasterItemService masterItemService;
    private final MasterItemProducer masterItemProducer;

    public MasterItemController(MasterItemService masterItemService, MasterItemProducer masterItemProducer)
    {
        this.masterItemService = masterItemService;
        this.masterItemProducer = masterItemProducer;
    }

    @PostMapping("/send")
    public String sendMasterItems() {
        List<MasterItem> masterItems = masterItemService.getAllMasterItems();
        LOGGER.info("Sending {} messages to Kafka.", masterItems.size());
        masterItems.forEach(masterItemProducer::sendMasterItem);
        return masterItems.size() + " messages sent to Kafka.";
    }

    @GetMapping
    public ResponseEntity<List<MasterItem>> getAllMasterItems() {
        List<MasterItem> masterItems = masterItemService.getAllMasterItems();
        return ResponseEntity.ok(masterItems);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<MasterItem> getMasterItemById(@PathVariable String itemId) {
        Optional<MasterItem> masterItem = masterItemService.getMasterItemById(itemId);
        return masterItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}