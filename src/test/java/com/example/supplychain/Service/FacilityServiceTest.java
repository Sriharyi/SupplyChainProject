package com.example.supplychain.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.supplychain.model.Address;
import com.example.supplychain.model.Facility;
import com.example.supplychain.model.Supplier;
import com.example.supplychain.repository.FacilityRepository;
import com.example.supplychain.service.impl.FacilityService;

@ExtendWith(MockitoExtension.class)
public class FacilityServiceTest {
    @Mock
    private FacilityRepository repo;

    @InjectMocks
    private FacilityService service;

    @Test
    public void testThatFacilityCanbeCreated() throws Exception {
        Facility facility = new Facility("cd", "Sai", new Address("aa", "aa", "aa", "aa", "aa"),
                new Supplier("aa", "ss", new Address("aa", "aa", "aa", "aa", "aa"), "aa", "aa", "aa", "aa", "aa", "aa"),
                new ArrayList<String>(Arrays.asList("aa")));
        Mockito.when(repo.save(facility)).thenReturn(facility);
        Facility result = service.saveData(facility);
        assertEquals(facility, result);
    }

    @Test
    public void testThatFacilityCanNotbeCreated() throws Exception {
        Facility facility = new Facility("cd", "Sai", new Address("aa", "aa", "aa", "aa", "aa"),
                new Supplier("aa", "ss", new Address("aa", "aa", "aa", "aa", "aa"), "aa", "aa", "aa", "aa", "aa", "aa"),
                new ArrayList<String>(Arrays.asList("aa")));
        Mockito.when(repo.save(facility)).thenThrow(RuntimeException.class);
        Facility result = service.saveData(facility);
        assertNotEquals(facility, result);
    }
}
