package com.example.supplychain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.supplychain.model.Brand;
import com.example.supplychain.repository.BrandRepository;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository repo;

    @InjectMocks
    private BrandService service;

    @Test
    public void testThatBrandCanbeCreated() throws Exception {
        Brand brand = new Brand("cd", "as", "ad", "aa", "sd");
        Mockito.when(repo.save(brand)).thenReturn(brand);
        Mockito.when(repo.existsById(Mockito.anyString())).thenReturn(false);
        Brand result = service.saveData(brand);
        assertEquals(brand, result);
    }

    @Test
    public void testThatBrandCanNotbeCreated() throws Exception {
        Brand brand = new Brand("cd", "as", "ad", "aa", "sd");
        Mockito.when(repo.existsById(Mockito.anyString())).thenReturn(true);
        Brand result = service.saveData(brand);
        assertNull(result);
    }

    @Test
    public void testThatBrandCanBeUpdated() throws Exception {
        Brand brand = new Brand("cd", "as", "ad", "aa", "sd");
        Mockito.when(repo.save(brand)).thenReturn(brand);
        Brand result = service.updateBrand(brand);
        assertEquals(brand, result);
    }
}
