package com.portfolio.sso.controllers;

import com.portfolio.sso.models.User;
import com.portfolio.sso.repository.UserRepository;
import com.portfolio.sso.security.services.CityService;
import com.portfolio.sso.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repo;

    @PostMapping("/avatar")
    public String updateUserAvatar(@RequestParam("userId") Long id, @RequestParam("image") MultipartFile multipartFile) {
        try {
            String url = userService.updateUserAvatar(id, multipartFile);
            return url;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/")
    public User accessUserById(@RequestParam("userId") Long id) {
        try {
            User user = repo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not Found with id:"));
            return user;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}