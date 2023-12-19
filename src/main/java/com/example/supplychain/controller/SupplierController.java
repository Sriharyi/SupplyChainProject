package com.example.supplychain.controller;

import java.io.IOException;
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

import com.example.supplychain.model.Supplier;
import com.example.supplychain.service.SupplierServiceInterface;


@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierServiceInterface service;

      //write crud operations here
    @GetMapping("/hello")
    public String get() {
        return "Hello World!";
    }

    //create and save the Supplier
    @PostMapping("/save")
    public ResponseEntity<Supplier> insertSupplier(@RequestBody Supplier supplier) {
        try {
            
            return new ResponseEntity<Supplier>(service.saveData(supplier),HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Supplier>(HttpStatus.BAD_REQUEST);
        }
    }
    

    //select all Supplier
    @GetMapping("/select/all")
    public ResponseEntity<List<Supplier>> selectAll() {
        try {
            return new ResponseEntity<List<Supplier>>( service.getAllSupplier(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( null,HttpStatus.BAD_REQUEST);
        }
    }


    //select Supplier by id;
    @GetMapping("/select/{id}")
    public ResponseEntity<Supplier> selectById(@PathVariable("id")String id){
        try {
            return new ResponseEntity<Supplier>( service.getById(id),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( null,HttpStatus.BAD_REQUEST);
        }
    }


    //update the Supplier;
    @PutMapping("update")
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier) {
        try {
            return new ResponseEntity<Supplier>(service.updateData(supplier),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Supplier>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable String id)
    {
        Boolean result =false;
        try {
            result = service.deleteData(id);
            if(result){
                    return  new ResponseEntity<String>("Deleted Successfully" ,HttpStatus.OK);
            }
            else return new ResponseEntity<String>( "Id not found",HttpStatus.NOT_FOUND);
           
        } catch (Exception e) {
            e.printStackTrace();
           return new ResponseEntity<String>( "Internal error",HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update/uploadimg/{id}")
    public ResponseEntity<?>  updateSupplierLogo(@PathVariable String id ,@RequestParam("image")MultipartFile image) throws IOException{
      try {
          Boolean result = false;
          if(service.getById(id)!=null)
          {
              result = service.updateImage(id, image);
              if(result)
               return ResponseEntity.ok("Image updated successfully for entity with id: "+id);
              else
                return new ResponseEntity<String>( "Image file only accepted",HttpStatus.BAD_REQUEST);
          }else
          {
             return new ResponseEntity<String>( "Id not found",HttpStatus.NOT_FOUND);
          }
      } catch (Exception e) {
          e.printStackTrace();
          return ResponseEntity.badRequest().build();
      }
    }    

  @GetMapping("getimage/{id}")
  public ResponseEntity<?> downloadSupplierLogo(@PathVariable String id) {
      byte[] imageData = service.downloadImage(id);
      return ResponseEntity.status(HttpStatus.OK)
                 .contentType(MediaType.valueOf("image/jpeg"))
                 .body(imageData);
  }

  @DeleteMapping("delete/image/{id}")
  public ResponseEntity<?> updateFiledinSupplier(@PathVariable String id){
     Boolean result = false;
     try {
         result = service.deleteImage(id);
         if(result)
         return ResponseEntity.ok("Image deleted successfully for entity ");
         else
         return ResponseEntity.notFound().build();
     } catch (Exception e) {
        e.printStackTrace();
         return ResponseEntity.badRequest().build();
     }
  }
  
}

// SupplierBuilder.builder()
//                 ._id(generateRandomHexString(24))
//                 .supplierName("Sample Supplier")
//                 .supplierAddress(Address.builder()
//                         .street("123 Main St")
//                         .city("Cityville")
//                         .pincode("12345")
//                         .state("Stateville")
//                         .country("Countryland")
//                         .build())
//                 .supplierContact("123-456-7890")
//                 .supplierEmail("sample@supplier.com")
//                 .supplierWebsite("www.sample-supplier.com")
//                 .supplierTierNo("Tier 1")
//                 .rawMaterial("Sample Raw Material")
//                 .styles("Sample Styles")
//                 .build();

// SupplierBuilder.builder()
//         ._id(generateRandomHexString(24))
//         .supplierName("Another Supplier")
//         .supplierAddress(SupplierBuilder.Address.builder()
//                 .street("456 Oak St")
//                 .city("Villagetown")
//                 .pincode("54321")
//                 .state("Stateland")
//                 .country("Countryville")
//                 .build())
//         .supplierContact("987-654-3210")
//         .supplierEmail("another@supplier.com")
//         .supplierWebsite("www.another-supplier.com")
//         .supplierTierNo("Tier 2")
//         .rawMaterial("Another Raw Material")
//         .styles("Another Styles")
//         .build();
// SupplierBuilder.builder()
//         ._id(generateRandomHexString(24))
//         .supplierName("Third Supplier")
//         .supplierAddress(SupplierBuilder.Address.builder()
//                 .street("789 Pine St")
//                 .city("Townsville")
//                 .pincode("98765")
//                 .state("Stateville")
//                 .country("Countryland")
//                 .build())
//         .supplierContact("555-123-4567")
//         .supplierEmail("third@supplier.com")
//         .supplierWebsite("www.third-supplier.com")
//         .supplierTierNo("Tier 3")
//         .rawMaterial("Third Raw Material")
//         .styles("Third Styles")
//         .build();