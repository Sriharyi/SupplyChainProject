package com.example.supplychain.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Supplier;

public interface SupplierServiceInterface {

    Supplier getById(String id);

    List<Supplier> getAllSupplier();

    Supplier saveData(Supplier supplier);

    Supplier updateData(Supplier supplier);

    Boolean deleteData(String id);

    Boolean updateImage(String id, MultipartFile image);

    byte[] downloadImage(String id) ;

    Boolean deleteImage(String id);

}
