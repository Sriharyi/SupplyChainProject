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
}
