package com.eureka.flame.main.services;

import com.eureka.flame.main.models.Blog;
import com.eureka.flame.main.models.User;
import com.eureka.flame.main.payload.request.BlogRequest;
import com.eureka.flame.main.repository.BlogRepository;
import com.eureka.flame.main.repository.UserRepository;
import com.eureka.flame.main.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public Blog createBlog(BlogRequest blog,  Long userId) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Blog b = new Blog(blog, user);
        String fileName = null;
        if (!blog.getThumbnailFile().isEmpty()) {
            fileName = StringUtils.cleanPath(blog.getThumbnailFile().getOriginalFilename());
        }
        String uploadDir = "images/";
        FileUploadUtil.saveFile(uploadDir, fileName, blog.getThumbnailFile());
        b.setThumbnail("/" + uploadDir + fileName);
        return blogRepository.save(b);
    }

    public Page<Blog> getAllBlog(Pageable pageable, String keyword) {
        Page<Blog> blogs = blogRepository.getAllBlog(pageable, keyword);
//        Page<BlogResponse> listBlog = blogRepository.getAllBlog(pageable, keyword);
        return blogs;
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteBlogById(Long id) {
        blogRepository.deleteById(id);
    }

    public Blog updateBlogById(BlogRequest blog, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Blog b = new Blog(blog, user);
        return blogRepository.save(b);
    }
}
