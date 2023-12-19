package com.example.supplychain.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
            System.out.println(service.getAllFacility());
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

    @PutMapping("updateImage/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable String id, @RequestParam("image") MultipartFile file) {
        try {
            if(file.getContentType()==null)
            return new ResponseEntity<>("Please Insert a file", HttpStatus.BAD_REQUEST);
            Facility facility=service.getById(id);
            String a=file.getContentType();
            if(a!=null && a.startsWith("image")){
            if(service.uploadImageToDB(facility, file))
            return new ResponseEntity<>(true, HttpStatus.OK);
            else
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }else
            return new ResponseEntity<String>(file.getContentType(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("downloadImage/{id}")
    public ResponseEntity<?> getImage(@PathVariable String id) {
        try {
            Facility facility=service.getById(id);
            if(!facility.equals(new Facility())){            
                return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/jpeg"))
				.body(service.downloadImage(facility));
            }
            else
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("deleteImage/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable String id) {
        try {
            Facility facility=service.getById(id);
            if(facility!= new Facility())
            return new ResponseEntity<>(service.deleteImage(facility), HttpStatus.OK);
            else
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
