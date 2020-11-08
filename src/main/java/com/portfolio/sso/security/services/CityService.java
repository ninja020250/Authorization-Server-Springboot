package com.portfolio.sso.security.services;

import com.portfolio.sso.models.City;
import com.portfolio.sso.repository.CityRepository;
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
