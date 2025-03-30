package com.effigoproject.transactionproducer.repository;

import com.effigoproject.transactionproducer.entity.MasterItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterItemRepository extends JpaRepository<MasterItem, String> {
    Page<MasterItem> findAll(Pageable pageable);
}