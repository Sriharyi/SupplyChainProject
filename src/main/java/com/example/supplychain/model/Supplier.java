package com.example.supplychain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("supplier")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {

    @Id
    private String _id;
    private String supplierName;
    private Address supplierAddress;
    private String supplierContact;
    private String supplierEmail;
    private String supplierWebsite;
    private String supplierTierNo;
    private String rawMaterial;
    private String styles;

    @Data
    @Builder
    public static class Address{
		private String street;
        private String city;
        private String pincode;
		private String state;
		private String country;
	}
}
