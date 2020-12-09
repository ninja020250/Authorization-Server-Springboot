package com.eureka.sso.main.services;

import com.eureka.sso.main.models.Profile;
import com.eureka.sso.main.models.User;
import com.eureka.sso.main.repository.ProfileRepository;
import com.eureka.sso.main.repository.UserRepository;
import com.eureka.sso.main.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    UserRepository userRepository;

    public boolean createAccount(User user, Profile profile) {
        User u = userRepository.save(user);
        profile.setId(u.getId());
        Profile p = profileRepository.save(profile);
        return true;
    }
}
