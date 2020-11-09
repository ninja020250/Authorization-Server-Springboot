package com.portfolio.sso.services;

import com.portfolio.sso.payload.request.CreateBlogRequest;
import com.portfolio.sso.payload.response.CreateBlogResponse;

import java.util.List;

public interface BlogService {
    public CreateBlogResponse createBlog(CreateBlogRequest blog);

    public List<CreateBlogResponse> getAllBlog();
}
