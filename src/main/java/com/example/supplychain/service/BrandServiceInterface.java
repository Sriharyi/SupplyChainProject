package com.example.supplychain.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Brand;

public interface BrandServiceInterface {

    Brand saveData(Brand brand) throws Exception;

    Brand updateBrand(Brand brand) throws Exception;

    String deleteData(String id) throws Exception;

    Brand getById(String id) throws Exception;

    List<Brand> getAllBrand() throws Exception;

    String updateImagePath(String id, MultipartFile file) throws Exception;

    void deleteImagePath(String id, String imagename) throws Exception;

    byte[] getImage(String id) throws IOException;

}
