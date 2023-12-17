package com.example.supplychain.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.supplychain.model.Address;
import com.example.supplychain.model.Facility;
import com.example.supplychain.model.Supplier;
import com.example.supplychain.repository.FacilityRepository;
import com.example.supplychain.service.FacilityServiceInterface;
import com.example.supplychain.service.impl.FacilityService;


@ExtendWith(MockitoExtension.class)
public class FacilityServiceTests {
    @Mock
    private FacilityRepository repo;
    
    @InjectMocks
    private FacilityService service;

    @Test
    public void testThatFacilityCanbeCreated() throws Exception{
        Facility facility=new Facility("cd","Sai",new Address("aa","aa","aa","aa","aa"), new Supplier("aa","ss",new Address("aa","aa","aa","aa","aa"),"aa","aa","aa","aa","aa","aa"),new ArrayList<String>(Arrays.asList("aa")));
        Mockito.when(repo.save(facility)).thenReturn(facility);
        Facility result = service.saveData(facility);
        assertEquals(facility, result);
    }

    @Test
    public void testThatFacilityCanNotbeCreated() throws Exception{
        Facility facility=new Facility("cd","Sai",new Address("aa","aa","aa","aa","aa"), new Supplier("aa","ss",new Address("aa","aa","aa","aa","aa"),"aa","aa","aa","aa","aa","aa"),new ArrayList<String>(Arrays.asList("aa")));
        Mockito.when(repo.save(facility)).thenThrow(RuntimeException.class);
        Facility result = service.saveData(facility);
        assertNotEquals(facility, result);
    }
}
