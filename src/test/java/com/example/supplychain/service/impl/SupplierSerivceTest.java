package com.example.supplychain.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Supplier;
import com.example.supplychain.repository.SupplierRepository;

@ExtendWith(MockitoExtension.class)
public class SupplierSerivceTest {


    @Mock
    private SupplierRepository repo;

    @InjectMocks
    private SupplierService service;

    @Test
    public  void testThatSuppliercanbeCreated(){

        Supplier supplier = Supplier.builder()._id("afgghjhyt").supplierName("sriharyi").build();

        Mockito.when(repo.save(Mockito.any(Supplier.class))).thenReturn(supplier);

        Supplier result = service.saveData(supplier);

        assertEquals(supplier, result);
    }

    @Test
    public  void testThatSuppliercanThrowExceptionwhenCreated(){

        Supplier supplier = Supplier.builder()._id("afgghjhyt").supplierName("sriharyi").build();

        Mockito.when(repo.save(Mockito.any(Supplier.class))).thenThrow(RuntimeException.class);

        Supplier result = service.saveData(supplier);

        assertNotEquals(supplier, result);
    }

    @Test
    public void testThatSuppliercanbeGetUsingId(){

        Supplier supplier = Supplier.builder()._id("afgghjhyt").supplierName("sriharyi").build();

        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(Optional.of(supplier));

        Supplier result = service.getById("afgghjhyt");

        assertEquals(supplier, result);

    }

    @Test
    public void testThatSuppliercanbeThrowAnExceptionwhenUseFindById(){
        Supplier supplier = Supplier.builder()._id("afgghjhyt").supplierName("sriharyi").build();

        Mockito.when(repo.findById(Mockito.anyString())).thenThrow(RuntimeException.class);

        Supplier result = service.getById("afgghjhyt");

        assertNotEquals(supplier, result);
    }

    @Test
    public void testThatSuppliercanbeUpdateSuccessfully(){
        Supplier supplier = Supplier.builder()._id("afgghjhyt").supplierName("sriharyi").build();

        Mockito.when(repo.save(Mockito.any(Supplier.class))).thenReturn(supplier);
        Mockito.when(repo.existsById(Mockito.anyString())).thenReturn(true);

        Supplier result = service.updateData(supplier);

        assertEquals(supplier, result);
    }

    @Test
    public void testThatSuppliercanThrowExceptionWhenUpdate(){
        Supplier supplier = Supplier.builder()._id("afgghjhyt").supplierName("sriharyi").build();

        Mockito.when(repo.save(Mockito.any(Supplier.class))).thenThrow(RuntimeException.class);

        Supplier result = service.saveData(supplier);

        assertNotEquals(supplier, result);
    }

    @Test
    public void testThatSupplierCanbeDeletedSuccessFully(){
        String supplierIdToDelete = "someSupplierId";

        Mockito.when(repo.existsById(Mockito.anyString())).thenReturn(true);
         
        // Mockito.when(repo.deleteById(Mockito.anyString())).thenReturn(true);

        Boolean result = service.deleteData(supplierIdToDelete);

        assertEquals(true, result);

    }

     @Test
    public void testThatSupplierCanNotbeDeletedSuccessFully(){
        String supplierIdToDelete = "someSupplierId";

        Mockito.when(repo.existsById(Mockito.anyString())).thenReturn(false);
         
        // Mockito.when(repo.deleteById(Mockito.anyString())).thenReturn(true);

        Boolean result = service.deleteData(supplierIdToDelete);

        assertEquals(false, result);
        
    }

     @Test
    public void testThatSupplierCanthrowExceptionWhenDeletionOperation(){
        String supplierIdToDelete = "someSupplierId";

        Mockito.when(repo.existsById(Mockito.anyString())).thenThrow(RuntimeException.class);
         
        // Mockito.when(repo.deleteById(Mockito.anyString())).thenReturn(true);

        Boolean result = service.deleteData(supplierIdToDelete);

        assertEquals(false, result);
        
    }

    @Test
    public void testThatSupplierUploadImageWorks(){
        Supplier supplier = Supplier.builder()
        ._id("6542334")
        .supplierName("Sample Supplier")
        .supplierContact("123-456-7890")
        .supplierEmail("sample@supplier.com")
        .supplierWebsite("www.sample-supplier.com")
        .supplierTierNo("Tier 1")
        .build();
        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(Optional.of(supplier));
        Mockito.when(repo.save(Mockito.any(Supplier.class))).thenReturn(Mockito.any(Supplier.class));
                 
        String name = "image";
        String originalFileName = "img.jpg";
        String contentType = "image/jpg";
        byte[] content = null;
        MultipartFile file = new MockMultipartFile(name,originalFileName, contentType, content);
        Boolean result =  service.updateImage("6542334", file);

        assertEquals(true, result);

    }

    @Test
    public void testThatSupplierUploadImageNotWorks(){
        Supplier supplier = Supplier.builder()
        ._id("6542334")
        .supplierName("Sample Supplier")
        .supplierContact("123-456-7890")
        .supplierEmail("sample@supplier.com")
        .supplierWebsite("www.sample-supplier.com")
        .supplierTierNo("Tier 1")
        .build();
        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(Optional.of(supplier));                 
        String name = "image";
        String originalFileName = "img.pdf";
        String contentType = "image/pdf";
        byte[] content = null;
        MultipartFile file = new MockMultipartFile(name,originalFileName, contentType, content);
        Boolean result =  service.updateImage("6542334", file);

        assertEquals(false, result);

    }

}