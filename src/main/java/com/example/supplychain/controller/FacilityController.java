package com.example.supplychain.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
    public ResponseEntity<Facility> insertFacility(@RequestBody Facility facility) {
        try {
            
            return new ResponseEntity<Facility>( service.saveData(facility),HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<Facility>( new Facility(),HttpStatus.BAD_REQUEST);
        }
    }
    

    //select all Facility
    @GetMapping("/select/all")
    public ResponseEntity<List<Facility>> selectAll() {
        try {
            return new ResponseEntity<List<Facility>>( service.getAllFacility(),HttpStatus.OK);
        } catch (Exception e) {
            
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<Facility>(Arrays.asList(new Facility())),HttpStatus.BAD_REQUEST);
        }
    }


    //select Facility by id;
    @GetMapping("/select/{id}")
    public ResponseEntity<Facility> selectById(@PathVariable("id")String id){
        try {
            if(service.getById(id)==null)
            return new ResponseEntity<>(new Facility(),HttpStatus.NOT_FOUND);
            else
            return new ResponseEntity<Facility>( service.getById(id),HttpStatus.OK);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<>( new Facility(),HttpStatus.BAD_REQUEST);
        }
    }


    //update the Facility;
    @PutMapping("update")
    public ResponseEntity<Facility> updateFacility(@RequestBody Facility facility) {
        try {
            return new ResponseEntity<Facility>( service.updateData(facility),HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<>(new Facility(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteFacility(@PathVariable String id)
    {
        try {
            if(service.getById(id)!=null)
            return new ResponseEntity<>(service.deleteData(id),HttpStatus.OK);
            else return new ResponseEntity<>(false,HttpStatus.NOT_FOUND) ;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST) ;
        }
    }



}
