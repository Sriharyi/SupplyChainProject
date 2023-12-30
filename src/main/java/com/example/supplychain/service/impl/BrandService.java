package com.example.supplychain.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Brand;
import com.example.supplychain.repository.BrandRepository;
import com.example.supplychain.service.BrandServiceInterface;

@Service
public class BrandService implements BrandServiceInterface {
    private String imageFolder = "E:/Intern/java/Springboot/SupplyChainProjectImages/";
    @Autowired
    private BrandRepository repo;

    @Override
    public Brand saveData(Brand brand) throws Exception {
        if (!repo.existsById(brand.get_id()))
            return repo.save(brand);
        return null;
    }

    @Override
    public Brand updateBrand(Brand brand) throws Exception {
        return repo.save(brand);
    }

    @Override
    public String deleteData(String id) throws Exception {
        repo.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public Brand getById(String id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Brand> getAllBrand() {
        return repo.findAll();
    }

    @Override
    public String updateImagePath(String id, MultipartFile file) throws Exception {
        String imagePath = imageFolder + file.getOriginalFilename();
        System.out.println(imagePath);

        try {
            File newfile = new File(imagePath);
            ImageIO.read(newfile);
            Brand brand = getById(id);
            brand.setPath(imagePath);
            updateBrand(brand);
            file.transferTo(new File(imagePath));
            return "Image Saved";
        } catch (Exception e) {
            return "No image file found";
        }
    }

    @Override
    public byte[] getImage(String id) throws IOException {
        Brand brand = getById(id);
        String imagePath = brand.getPath();
        byte[] images = Files.readAllBytes(new File(imagePath).toPath());
        return images;
    }

    @Override
    public void deleteImagePath(String id, String imagename) throws Exception {
        Brand brand = getById(id);
        brand.setPath("");
        updateBrand(brand);
        File target = new File(imageFolder + imagename);
        target.delete();
    }
}