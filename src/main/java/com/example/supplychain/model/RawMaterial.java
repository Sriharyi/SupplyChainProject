package com.example.supplychain.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("rawMaterials")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawMaterial {

    @Id
    private String _id;

    private String rawMaterialName; 

    private String rawMaterialType;
    
    @DocumentReference(collection = "facilities")
    private Facility facilityId;

    @DocumentReference(collection = "suppliers")
    private Supplier Supplier;

    @DocumentReference(collection = "rawMaterials")
    private ArrayList<RawMaterial>  rawMaterials;
  
    private ArrayList<MaterialComposition> materialComposition;
   
}

