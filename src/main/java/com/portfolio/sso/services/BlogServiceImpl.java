package com.portfolio.sso.services;

import com.portfolio.sso.models.Blog;
import com.portfolio.sso.models.User;
import com.portfolio.sso.payload.request.BlogRequest;
import com.portfolio.sso.payload.response.BlogResponse;
import com.portfolio.sso.repository.BlogRepository;
import com.portfolio.sso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public BlogResponse createBlog(BlogRequest blog) {
        User user = userRepository.findById(blog.getUserId()).orElseThrow(EntityNotFoundException::new);
        Blog b = new Blog(blog, user);
        Blog res = blogRepository.save(b);
        return res.toResponse();
    }

    public Page<BlogResponse> getAllBlog(Pageable pageable, String keyword) {
        Page<BlogResponse> listBlog = blogRepository.getAllBlog(pageable, keyword);
        return listBlog;
    }

    public BlogResponse getBlogById(Long id){
        Blog blog = blogRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return blog.toResponse();
    }

    public void deleteBlogById(Long id){
      blogRepository.deleteById(id);
    }

    public BlogResponse updateBlogById(BlogRequest blog){
        User user = userRepository.findById(blog.getUserId()).orElseThrow(EntityNotFoundException::new);
        Blog b = new Blog(blog, user);
        Blog res = blogRepository.save(b);
        return res.toResponse();
    }
}
