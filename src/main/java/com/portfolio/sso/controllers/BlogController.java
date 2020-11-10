package com.portfolio.sso.controllers;

import com.portfolio.sso.payload.request.BlogRequest;
import com.portfolio.sso.payload.request.PageableRequest;
import com.portfolio.sso.payload.response.BlogResponse;
import com.portfolio.sso.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public BlogResponse createBlog(@Valid @RequestBody BlogRequest req) {
        return blogService.createBlog(req);
    }

    @GetMapping("")
    public Page<BlogResponse> getAllBlog(
            PageableRequest params
    ) {
        Sort sortable = Sort.by(params.getSortField()).ascending();
        if (params.getSort().equals("DESC")) {
            sortable = Sort.by(params.getSortField()).descending();
        }
        Pageable pageable = PageRequest.of(params.getPage(), params.getSize(), sortable);
        return blogService.getAllBlog(pageable, params.getSearch());
    }
}
