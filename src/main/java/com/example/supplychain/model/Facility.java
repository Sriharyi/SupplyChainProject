package com.example.supplychain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("facilities")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Facility {
    
    @Id
    private String _id;
    private String facilityName;
    private FacilityAddress facilityAddress;
    @DocumentReference(collection = "suppliers")
    private Supplier supplierId;
    private String facilityPhoto;
}
