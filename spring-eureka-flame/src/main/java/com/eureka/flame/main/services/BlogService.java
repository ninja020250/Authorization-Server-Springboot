package com.eureka.flame.main.services;

import com.eureka.flame.main.models.Blog;
import com.eureka.flame.main.payload.request.BlogRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface BlogService {
    public Blog createBlog(BlogRequest blog, Long userId) throws IOException;

    public Page<Blog> getAllBlog(Pageable pageable, String keyword);

    public Blog getBlogById(Long id);

    public void deleteBlogById(Long id);

    public Blog updateBlogById(BlogRequest blog, Long userId);
}
