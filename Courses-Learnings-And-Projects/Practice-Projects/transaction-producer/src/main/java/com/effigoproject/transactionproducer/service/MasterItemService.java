package com.effigoproject.transactionproducer.service;

import com.effigoproject.transactionproducer.constants.AppConstants;
import com.effigoproject.transactionproducer.entity.MasterItem;
import com.effigoproject.transactionproducer.repository.MasterItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterItemService {

    private final MasterItemRepository masterItemRepository;

    public List<MasterItem> getAllMasterItems() {
        Pageable pageable = PageRequest.of(AppConstants.DEFAULT_PAGE_NUMBER, AppConstants.DEFAULT_PAGE_SIZE);
        return masterItemRepository.findAll(pageable).getContent();
    }

    public Optional<MasterItem> getMasterItemById(String itemId) {
        return masterItemRepository.findById(itemId);
    }
}

