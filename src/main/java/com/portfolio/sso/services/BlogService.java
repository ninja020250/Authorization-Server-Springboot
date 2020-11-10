package com.portfolio.sso.services;

import com.portfolio.sso.payload.request.BlogRequest;
import com.portfolio.sso.payload.response.BlogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    public BlogResponse createBlog(BlogRequest blog);

    public Page<BlogResponse> getAllBlog(Pageable pageable, String keyword);
}
