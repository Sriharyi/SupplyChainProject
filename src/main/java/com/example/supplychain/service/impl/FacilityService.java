package com.example.supplychain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supplychain.model.Facility;
import com.example.supplychain.repository.FacilityRepository;
import com.example.supplychain.service.FacilityServiceInterface;

@Service
public class FacilityService implements FacilityServiceInterface{

    @Autowired
    private FacilityRepository repo;

    @Override
    public Facility saveData(Facility facility) {
      Facility fac=new Facility();
        try {
           fac= repo.save(facility);
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        return fac;
    }

    @Override
    public List<Facility> getAllFacility() {
      return  repo.findAll(); 
    }

    @Override
    public Facility getById(String id) {
      Optional<Facility> result = repo.findById(id);
      return result.get();
    }

    @Override
    public Facility updateData(Facility facility) {
        return repo.save(facility);
    }

    @Override
    public Boolean deleteData(String id) {
        repo.deleteById(id);
        return true;
    }

}
