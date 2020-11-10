package com.portfolio.sso.services;

import com.portfolio.sso.models.User;
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
    private UserRepository repo;

    public String updateUserAvatar(Long id, MultipartFile multipartFile)  throws IOException  {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        User user = repo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not Found with id:"));
        user.setPhotos(fileName);
        User savedUser = repo.save(user);
        String uploadDir = "user-photos/" + savedUser.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "/user-photos/" + id + "/" + fileName;
    }
}
