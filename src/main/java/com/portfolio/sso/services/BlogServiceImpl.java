package com.portfolio.sso.services;

import com.portfolio.sso.models.Blog;
import com.portfolio.sso.models.User;
import com.portfolio.sso.payload.request.CreateBlogRequest;
import com.portfolio.sso.payload.response.CreateBlogResponse;
import com.portfolio.sso.repository.BlogRepository;
import com.portfolio.sso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public CreateBlogResponse createBlog(CreateBlogRequest blog) {
        User user = userRepository.findById(blog.getUserId()).orElseThrow(EntityNotFoundException::new);
        Blog b = new Blog(blog, user);
        Blog res = blogRepository.save(b);
        return res.toResponse();
    }

    public List<CreateBlogResponse> getAllBlog() {
        List<Blog> listBlog = blogRepository.findAll();
        List<CreateBlogResponse> res = new ArrayList<>();
        for (int i = 0; i < listBlog.size(); i++) {
            res.add(listBlog.get(i).toResponse());
        }
        return res;
    }
}
