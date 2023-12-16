package com.example.supplychain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.supplychain.model.Facility;
import com.example.supplychain.service.FacilityServiceInterface;

@RestController
@RequestMapping(path = "/facility")
public class FacilityController {

    @Autowired
    private FacilityServiceInterface service;

     //write crud operations here
    @GetMapping("/hello")
    public String get() {
        return "Hello World!";
    }

    //create and save the Facility
    @PostMapping("/save")
    public ResponseEntity<String> insertFacility(@RequestBody Facility facility) {
        try {
            service.saveData(facility);
            return new ResponseEntity<String>( "Inserted Successfully",HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<String>( "Internal error",HttpStatus.BAD_REQUEST);
        }
    }
    

    //select all Facility
    @GetMapping("/select/all")
    public ResponseEntity<List<Facility>> selectAll() {
        try {
            return new ResponseEntity<List<Facility>>( service.getAllFacility(),HttpStatus.OK);
        } catch (Exception e) {
            
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }


    //select Facility by id;
    @GetMapping("/select/{id}")
    public ResponseEntity<Facility> selectById(@PathVariable("id")String id){
        try {
            return new ResponseEntity<Facility>(service.getById(id),HttpStatus.OK);
        } catch (Exception e) {
           
            e.printStackTrace();
            return new ResponseEntity<>( null,HttpStatus.BAD_REQUEST);
        }
    }


    //update the Facility;
    @PutMapping("update")
    public ResponseEntity<String> updateFacility(@RequestBody Facility facility) {
        try {
            service.updateFacility(facility); 
            return new ResponseEntity<String>( "Updated Successfully",HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<String>("Internal error",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteFacility(@PathVariable String id)
    {
        try {
            if(service.getById(id)==null)
            service.deleteData(id);
            else return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND) ;
            return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);     
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<String>("Internal error",HttpStatus.BAD_REQUEST) ;
        }
    }



}
