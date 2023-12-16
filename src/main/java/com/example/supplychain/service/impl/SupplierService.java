package com.example.supplychain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supplychain.model.Supplier;
import com.example.supplychain.repository.SupplierRepository;
import com.example.supplychain.service.SupplierServiceInterface;

@Service
public class SupplierService implements SupplierServiceInterface{

    @Autowired
    private SupplierRepository repo;

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
         if(repo.existsById(id))
         {
            repo.deleteById(id);
            return true;
         }
         else 
         {
            return false;
         }
    }

}
