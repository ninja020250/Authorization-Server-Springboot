package com.eureka.flame.main.security.services;

import com.eureka.flame.main.models.City;
import com.eureka.flame.main.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public List<City> loadAllCity() {
        return cityRepository.findAll();
    }
}
