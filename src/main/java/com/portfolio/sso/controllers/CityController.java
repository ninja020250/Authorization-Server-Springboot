package com.portfolio.sso.controllers;

import com.portfolio.sso.models.City;
import com.portfolio.sso.security.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/all")
    public List<City> cityAccess(){
        return cityService.loadAllCity();
    }
}
