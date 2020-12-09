package com.eureka.flame.main.services;

import com.eureka.flame.main.models.Profile;
import com.eureka.flame.main.models.User;
import com.eureka.flame.main.repository.ProfileRepository;
import com.eureka.flame.main.repository.UserRepository;
import com.eureka.flame.main.utils.FileUploadUtil;
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

    public String updateUserAvatar(Long id, MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not Found with id:"));
        profile.setPhotos(fileName);
        Profile savedUser = profileRepository.save(profile);
        String uploadDir = "/images/user-photos/" + savedUser.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "/images/user-photos/" + id + "/" + fileName;
    }

    public boolean createAccount(User user, Profile profile) {
        User u = userRepository.save(user);
        profile.setId(u.getId());
        Profile p = profileRepository.save(profile);
        return true;
    }
}
