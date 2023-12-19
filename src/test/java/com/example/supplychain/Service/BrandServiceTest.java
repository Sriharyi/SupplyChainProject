package com.example.supplychain.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.supplychain.model.Address;
import com.example.supplychain.model.Brand;
import com.example.supplychain.model.Facility;
import com.example.supplychain.model.Supplier;
import com.example.supplychain.repository.BrandRepository;
import com.example.supplychain.service.impl.BrandService;

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
        Brand result = service.saveData(brand);
        assertEquals(brand, result);
    }

    @Test
    public void testThatBrandCanNotbeCreated() throws Exception {
        Brand brand = new Brand("cd", "as", "ad", "aa", "sd");
        Mockito.when(repo.save(Mockito.<Brand>any())).thenThrow(RuntimeException.class);
        Brand result = service.saveData(brand);
        assertNotEquals(brand, result);
    }
}
