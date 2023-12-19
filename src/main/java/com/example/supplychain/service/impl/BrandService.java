package com.example.supplychain.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.awt.image.BufferedImage;
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

    // @Override
    // public Brand saveData(Brand brand) {
    // Brand b = new Brand();
    // try {
    // b = repo.save(brand);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return b;
    // }

    @Override
    public Brand saveData(Brand brand) {
        try {
            if (!repo.existsById(brand.get_id()))
                return repo.save(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBrand(Brand brand) {
        repo.save(brand);
    }

    @Override
    public Boolean deleteData(String id) {
        Boolean result = false;
        try {
            if (repo.existsById(id)) {
                repo.deleteById(id);
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
    public String updateImagePath(String id, MultipartFile file) throws IOException {
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
        } catch (IOException e) {
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
    public void deleteImagePath(String id, String imagename) throws IOException {
        Brand brand = getById(id);
        brand.setPath("");
        updateBrand(brand);
        File target = new File(imageFolder + imagename);
        target.delete();
    }
}