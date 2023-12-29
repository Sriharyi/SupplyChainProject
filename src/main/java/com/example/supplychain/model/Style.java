package com.example.supplychain.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("styles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Style {

    @Id
    private String _id;

    private String styleName;

    private String styleType;

    @DocumentReference(collection = "facilities")
    private Facility facilityId;

    @DocumentReference(collection = "suppliers")
    private Supplier supplierId;

    @DocumentReference(collection = "rawMaterials")
    private ArrayList<RawMaterial> RawMaterials;

    @DocumentReference(collection = "brands")
    private Brand brand;
}
