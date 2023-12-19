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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Brand;
import com.example.supplychain.service.BrandServiceInterface;

@RestController
@RequestMapping(path = "brand")
public class BrandController {

    @Autowired
    private BrandServiceInterface service;

    @GetMapping("/test")
    public ResponseEntity<String> get() {
        return new ResponseEntity<String>("In Brand", HttpStatus.OK);
    }

    // create and save the Brand
    @PostMapping("/save")
    public ResponseEntity<Brand> insertBrand(@RequestBody Brand brand) {
        try {
            Brand b = service.saveData(brand);
            if (b != null)
                return new ResponseEntity<Brand>(b, HttpStatus.CREATED);
            return new ResponseEntity<Brand>(b, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Brand>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/saveImage")
    public ResponseEntity<String> insertBrandImage(@PathVariable String id, @RequestParam("image") MultipartFile file) {
        try {
            String result = service.updateImagePath(id, file);
            if (result.equals("Image saved"))
                return new ResponseEntity<String>(result, HttpStatus.CREATED);
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/deleteImage/{imagename}")
    public ResponseEntity<String> deleteBrandImage(@PathVariable String id, @PathVariable String imagename) {
        try {
            service.deleteImagePath(id, imagename);
            return new ResponseEntity<String>("image deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/getImage/")
    public ResponseEntity<byte[]> getBrandImage(@PathVariable String id) {
        try {
            return new ResponseEntity<byte[]>(service.getImage(id), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }
    }

    // select all Brand
    @GetMapping("/select/all")
    public ResponseEntity<List<Brand>> selectAll() {
        try {
            List<Brand> brands = service.getAllBrand();
            if (brands == null) {
                return new ResponseEntity<>(new ArrayList<Brand>(Arrays.asList(new Brand())), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(brands, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // select Brand by id;
    @GetMapping("/select/{id}")
    public ResponseEntity<Brand> selectById(@PathVariable("id") String id) {
        try {
            Brand brand = service.getById(id);
            if (brand == null) {
                return new ResponseEntity<>(new Brand(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(brand, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update the Brand;
    @PutMapping("update")
    public ResponseEntity<String> updateBrand(@RequestBody Brand brand) {
        try {
            service.updateBrand(brand);
            return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Internal error", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable String id) {
        try {
            if (service.getById(id) != null)
                service.deleteData(id);
            else
                return new ResponseEntity<String>("Id not found", HttpStatus.NOT_FOUND);
            return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<String>("Internal error", HttpStatus.BAD_REQUEST);
        }
    }
}
