package com.portfolio.sso.services;

import com.portfolio.sso.dtos.BlogDTO;
import com.portfolio.sso.models.Blog;
import com.portfolio.sso.models.User;
import com.portfolio.sso.payload.request.BlogRequest;
import com.portfolio.sso.payload.response.BlogResponse;
import com.portfolio.sso.repository.BlogRepository;
import com.portfolio.sso.repository.UserRepository;
import com.portfolio.sso.utils.FileUploadUtil;
import com.portfolio.sso.utils.PagingAndSortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public Blog createBlog(BlogRequest blog) throws IOException {
        User user = userRepository.findById(blog.getUserId()).orElseThrow(EntityNotFoundException::new);
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

    public Blog updateBlogById(BlogRequest blog) {
        User user = userRepository.findById(blog.getUserId()).orElseThrow(EntityNotFoundException::new);
        Blog b = new Blog(blog, user);
        return blogRepository.save(b);
    }
}
