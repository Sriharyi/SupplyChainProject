package com.example.supplychain.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Document("facility")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Facility {
    
    @Id
    private String _id;
    private String facilityName;
    private String facilityAddress;
    @DocumentReference(collection = "supplier")
    private Supplier supplierId;
    // private ArrayList<String> certifications;

    // @Data
    // public class Address{
	// 	private String street;
    //     private String city;
    //     private String pincode;
	// 	private String state;
	// 	private String country;
	// }
}
