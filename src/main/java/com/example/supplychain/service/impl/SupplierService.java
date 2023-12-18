package com.example.supplychain.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Supplier;
import com.example.supplychain.repository.SupplierRepository;
import com.example.supplychain.service.SupplierServiceInterface;

@Service
public class SupplierService implements SupplierServiceInterface{

    @Autowired
    private SupplierRepository repo;

    @Value("${upload-dir}")
    private String uploadDir;

    @Override
    public Supplier getById(String id) {
       Optional<Supplier> result = Optional.of(new Supplier());
       try {
          result = repo.findById(id);
      } catch (Exception e) {
         e.printStackTrace();
      }
       return result.isPresent()?result.get():result.orElse(result.get());
    }

    @Override
    public List<Supplier> getAllSupplier() {
       return repo.findAll();
    }

    @Override
    public Supplier saveData(Supplier supplier) {
       Supplier supp = new Supplier();  
      try {
          supp = repo.save(supplier);

      } catch (Exception e) {
         
         e.printStackTrace();
      }
      return supp;
    }

    @Override
    public Supplier updateData(Supplier supplier) {
      Supplier supp=new Supplier();
      try {
         if (repo.existsById(supplier.get_id())) {
            supp = repo.save(supplier);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return supp;
       
    }

    @Override
    public Boolean deleteData(String id) {
      Boolean result=false;
         try {
            if(repo.existsById(id))
            {
               repo.deleteById(id);
               result = true;
            }
            else 
            {
               result = false;
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      return  result;
    }

   @Override
   public Supplier updateImage(String id, MultipartFile image) {
        Supplier entity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found with id: " + id));

        // Save the image file
        String imagePath = saveImage(image);
        entity.setSupplierlogoName(image.getOriginalFilename());
        entity.setSupplierlogoPath(imagePath);
        entity.setSupplierlogoType(image.getContentType());
        return repo.save(entity);
   }


    private String saveImage(MultipartFile imageFile) {
        try {
            String filepath = uploadDir+imageFile.getOriginalFilename();
            imageFile.transferTo(new File(filepath));
            return filepath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }

   @Override
   public byte[] downloadImage(String id) {
      Supplier supplier = repo.findById(id).orElseThrow(()->new RuntimeException("Entity not found with id: " + id));
      String imagePath = supplier.getSupplierlogoPath();
      byte[] imageData;
      try {
         if(imagePath.equals("")|| imagePath == null)
         {
               imageData = null;
               throw new RuntimeException("Failed to download image because not available");
         }
         imageData = Files.readAllBytes(new File(imagePath).toPath());
      } catch (IOException e) {
          throw new RuntimeException("Failed to download image", e);
      }
      return imageData;
   }

   @Override
   public Boolean deleteImage(String id) {
      Supplier supplier = repo.findById(id).orElseThrow(()->new RuntimeException("Entity not found with id: " + id));
      Boolean result = false;
      try {
         File file=new File(supplier.getSupplierlogoPath());
         result = file.delete();
         // result = Files.deleteIfExists(new File(supplier.getSupplierlogoPath()).toPath());
      } catch (Exception e) {
         e.printStackTrace();
      }
      supplier.setSupplierlogoPath("");
      supplier.setSupplierlogoName("");
      supplier.setSupplierlogoType("");
      updateData(supplier);
      return result;
   }


}
