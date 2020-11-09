package com.portfolio.sso.controllers;

import com.portfolio.sso.models.Blog;
import com.portfolio.sso.payload.request.CreateBlogRequest;
import com.portfolio.sso.payload.response.CreateBlogResponse;
import com.portfolio.sso.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("")
    public CreateBlogResponse createBlog(@Valid @RequestBody CreateBlogRequest req){
        return blogService.createBlog(req);
    }

    @GetMapping("")
    public Page<CreateBlogResponse> getAllBlog(
                @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort
    ){
        Sort sortable = Sort.by("title").ascending();
        if (sort.equals("ASC")) {
            sortable = Sort.by("title").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("title").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);
        return blogService.getAllBlog(pageable);
    }
}
