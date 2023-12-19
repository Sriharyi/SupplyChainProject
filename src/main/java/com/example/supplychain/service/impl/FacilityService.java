package com.example.supplychain.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Facility;
import com.example.supplychain.repository.FacilityRepository;
import com.example.supplychain.service.FacilityServiceInterface;

@Service
public class FacilityService implements FacilityServiceInterface {

  @Autowired
  private FacilityRepository repo;

  @Override
  public Facility saveData(Facility facility) {
    Facility fac = new Facility();
    try {
      fac = repo.save(facility);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fac;
  }

  @Override
  public List<Facility> getAllFacility() {
    try {
      return repo.findAll();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Facility getById(String id) {
    try {
      Optional<Facility> result = repo.findById(id);
      return result.get();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Facility updateData(Facility facility) {
    try {

      return repo.save(facility);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Boolean deleteData(String id) {
    try {
      repo.deleteBy_id(id);
      return true;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Boolean uploadImageToDB(Facility facility, MultipartFile file) {
    try {
      String filePath = "D:/SupplyChainProject/src/main/resources/images/facilitiesImage/" + file.getOriginalFilename();
      facility.setFacilityPhoto(filePath);
      file.transferTo(new File(filePath));
      if (updateData(facility) != null)
        return true;
      else
        return false;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }

  }

  @Override
  public byte[] downloadImage(Facility facility) {
    String filePath = facility.getFacilityPhoto();
    try {
      if (filePath == null)
        return null;
      byte[] images = Files.readAllBytes(new File(filePath).toPath());
      return images;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Boolean deleteImage(Facility facility) {
    File file = new File(facility.getFacilityPhoto());
    file.delete();
    facility.setFacilityPhoto(null);
    updateData(facility);
    return true;
  }

}
