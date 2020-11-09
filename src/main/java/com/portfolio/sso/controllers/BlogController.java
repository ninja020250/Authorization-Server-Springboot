package com.portfolio.sso.controllers;

import com.portfolio.sso.payload.request.CreateBlogRequest;
import com.portfolio.sso.payload.response.CreateBlogResponse;
import com.portfolio.sso.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CreateBlogResponse> getAllBlog(){
        return blogService.getAllBlog();
    }
}
