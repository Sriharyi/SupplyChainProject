package com.example.supplychain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.supplychain.model.Facility;

@Repository
public interface FacilityRepository extends MongoRepository<Facility,String>{
 Boolean deleteBy_id(String _id);
}
