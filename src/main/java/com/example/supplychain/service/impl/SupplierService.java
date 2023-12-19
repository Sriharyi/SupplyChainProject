package com.example.supplychain.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.apache.commons.imaging.Imaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Supplier;
import com.example.supplychain.repository.SupplierRepository;
import com.example.supplychain.service.SupplierServiceInterface;

@Service
public class SupplierService implements SupplierServiceInterface{

    @Autowired
    private SupplierRepository repo;

    
    private final String UPLOAD_DIR = "/home/sriharyi/Documents/InternTraining/BackEnd/Spring-boot/Projects/SupplyChain/SupplyChainProject/src/main/resources/images/suppliersImage/";

    @Override
    public Supplier getById(String id) {
       Optional<Supplier> result = Optional.of(new Supplier());
       try {
          result = repo.findById(id);
      } catch (Exception e) {
         e.printStackTrace();
      }
       return result.get();
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
   public Boolean updateImage(String id, MultipartFile image) {
      Supplier entity = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Entity not found with id: " + id));
      Boolean result = false;
      try {
         if (isImage(image)) {
            String imagePath = saveImage(image);
            entity.setSupplierlogoName(image.getOriginalFilename());
            entity.setSupplierlogoPath(imagePath);
            entity.setSupplierlogoType(image.getContentType());
            repo.save(entity);
            result = true;
        }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;
   }
   private Boolean isImage(MultipartFile multipartFile) {
        try {
            File file = convertMultipartFileToFile(multipartFile);
            return Imaging.hasImageFileExtension(file);
        } catch (IOException e) {
            return false;
        }
    }

    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
      File file = new File(multipartFile.getOriginalFilename());
      Files.copy(multipartFile.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      return file;
  }


   // private boolean isSupportedContentType(String contentType) {
   //    return contentType.equals("image/png")
   //          || contentType.equals("image/jpg")
   //          || contentType.equals("image/jpeg");
   // }

   private String saveImage(MultipartFile imageFile) {
      try {
         String filepath = UPLOAD_DIR + imageFile.getOriginalFilename();
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
