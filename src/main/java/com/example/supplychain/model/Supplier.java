package com.example.supplychain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {

    @Id
    private String _id;
    private String supplierName;
    private String supplierContact;
    private String supplierEmail;
    private String supplierWebsite;
    private String supplierTierNo;
    private String supplierlogoName;
    private String supplierlogoType;
    private String supplierlogoPath;
}
