package com.portfolio.sso.services;

import com.portfolio.sso.dtos.BlogDTO;
import com.portfolio.sso.models.Blog;
import com.portfolio.sso.payload.request.BlogRequest;
import com.portfolio.sso.payload.response.BlogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    public Blog createBlog(BlogRequest blog) throws IOException;

    public Page<Blog> getAllBlog(Pageable pageable, String keyword);

    public Blog getBlogById(Long id);

    public void deleteBlogById(Long id);

    public Blog updateBlogById(BlogRequest blog);
}
