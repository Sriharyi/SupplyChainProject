package com.example.supplychain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    private String _id;
    private String brandName;
    private String parentCompany;
    private String website;
    private String path = "";
}
