package com.portfolio.sso.services;

import com.portfolio.sso.models.Blog;
import com.portfolio.sso.payload.request.CreateBlogRequest;
import com.portfolio.sso.payload.response.CreateBlogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    public CreateBlogResponse createBlog(CreateBlogRequest blog);

    public Page<Blog> getAllBlog(Pageable pageable);
}
