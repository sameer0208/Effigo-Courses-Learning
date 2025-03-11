package com.effigoproject.transactionproducer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_item", schema = "masters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterItem {

    @Id
    @Column(name = "item_id", length = 255)
    private String itemId;

    @Column(name = "client_id", length = 255)
    private String clientId;

    @Column(name = "created_by", length = 255)
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "estimated_price", precision = 19, scale = 2)
    private BigDecimal estimatedPrice;

    @Column(name = "generic_masters")
    private Integer genericMasters;

    @Column(name = "gl_code", length = 255)
    private String glCode;

    @Column(name = "hsn_code", length = 255)
    private String hsnCode;

    @Column(name = "item_category_id", length = 255)
    private String itemCategoryId;

    @Column(name = "item_category_name", length = 255)
    private String itemCategoryName;

    @Column(name = "item_code", length = 255)
    private String itemCode;

    @Column(name = "item_name", length = 255)
    private String itemName;

    @Column(name = "item_sub_category_id", length = 255)
    private String itemSubCategoryId;

    @Column(name = "l3_category_id", length = 255)
    private String l3CategoryId;

    @Column(name = "lead_buyer_id", length = 255)
    private String leadBuyerId;

    @Column(name = "market_price", precision = 19, scale = 2)
    private BigDecimal marketPrice;

    @Column(name = "masters_type")
    private Integer mastersType;

    @Column(name = "quality")
    private Integer quality;

    @Column(name = "quality_assurance")
    private Boolean qualityAssurance;

    @Column(name = "status")
    private Integer status;

    @Column(name = "sub_category_name", length = 255)
    private String subCategoryName;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "uom", length = 255)
    private String uom;

    @Column(name = "generic_item")
    private Boolean genericItem;

    @Column(name = "cus_field_1", length = 255)
    private String cusField1;

    @Column(name = "cus_field_2", length = 255)
    private String cusField2;

    @Column(name = "cus_field_3", length = 255)
    private String cusField3;

    @Column(name = "cus_field_4", length = 255)
    private String cusField4;

    @Column(name = "cus_field_5", length = 255)
    private String cusField5;

    @Column(name = "cus_field_6", length = 255)
    private String cusField6;

    @Column(name = "cus_field_7", length = 255)
    private String cusField7;

    @Column(name = "cus_field_8", length = 255)
    private String cusField8;

    @Column(name = "cus_field_9", length = 255)
    private String cusField9;

    @Column(name = "cus_field_10", length = 255)
    private String cusField10;

    @Column(name = "item_description", length = 255)
    private String itemDescription;

    @Column(name = "long_text", length = 255)
    private String longText;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "partition_group", length = 255)
    private String partitionGroup;

    @Column(name = "sub_category", length = 255)
    private String subCategory;

}

