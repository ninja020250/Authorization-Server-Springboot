package com.portfolio.sso.controllers;

import com.portfolio.sso.models.Profile;
import com.portfolio.sso.models.User;
import com.portfolio.sso.repository.ProfileRepository;
import com.portfolio.sso.repository.UserRepository;
import com.portfolio.sso.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private JavaMailSender javaMailSender;

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
            sendEmail();
            return user;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserProfileById(@PathVariable Long id) {
        try {
            Profile user = profileRepository.findProfileById(id).orElseThrow(NullPointerException::new);
            return ResponseEntity.ok(user);
        } catch (NullPointerException e) {
            return ResponseEntity
                    .notFound().build();
        }
    }

    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("nhatcuonghuynh@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
}
