package com.example.supplychain.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Facility;

public interface FacilityServiceInterface {

    Facility saveData(Facility facility);

    List<Facility> getAllFacility();

    Facility getById(String id);

    Facility updateData(Facility facility);

    Boolean deleteData(String id);

    Boolean uploadImageToDB(Facility facility,MultipartFile file);

    byte[] downloadImage(Facility facility);

    Boolean deleteImage(Facility facility);


}
