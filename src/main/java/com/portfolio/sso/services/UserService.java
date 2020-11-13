package com.portfolio.sso.services;

import com.portfolio.sso.models.Profile;
import com.portfolio.sso.models.User;
import com.portfolio.sso.repository.ProfileRepository;
import com.portfolio.sso.repository.UserRepository;
import com.portfolio.sso.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

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
        String uploadDir = "user-photos/" + savedUser.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "/user-photos/" + id + "/" + fileName;
    }

    public boolean createAccount(User user, Profile profile) {
        User u = userRepository.save(user);
        profile.setId(u.getId());
        Profile p = profileRepository.save(profile);
        return true;
    }
}
