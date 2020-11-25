package com.portfolio.sso.controllers;

import com.portfolio.sso.dtos.BlogDTO;
import com.portfolio.sso.models.Blog;
import com.portfolio.sso.payload.request.BlogRequest;
import com.portfolio.sso.payload.request.PageableRequest;
import com.portfolio.sso.payload.response.PagingObject;
import com.portfolio.sso.payload.response.ResponseObject;
import com.portfolio.sso.payload.response.enums.ResponseStatus;
import com.portfolio.sso.security.services.UserDetailsImpl;
import com.portfolio.sso.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> createBlog(@ModelAttribute BlogRequest req) throws IOException {
        UserDetailsImpl u = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Blog blog = blogService.createBlog(req, u.getId());
        BlogDTO.BlogResponseDTO response = BlogDTO.BlogToBlogResponse(blog);

        return ResponseEntity.ok(ResponseObject.builder()
                .data(response)
                .status(ResponseStatus.SUCCESS)
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllBlog(
            PageableRequest params
    ) {
        Sort sortable = Sort.by(params.getSortField()).ascending();
        if (params.getSort().equals("DESC")) {
            sortable = Sort.by(params.getSortField()).descending();
        }
        Pageable pageable = PageRequest.of(params.getPage(), params.getSize(), sortable);
        Page<Blog> blogDTOs = blogService.getAllBlog(pageable, params.getSearch());
        List<BlogDTO.BlogResponseDTO> blogResponseDTO = blogDTOs.stream()
                .map(BlogDTO::BlogToBlogResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ResponseObject.builder()
                .data(blogResponseDTO)
                .pagination(new PagingObject(blogDTOs.getNumber() + 1, blogDTOs.getNumberOfElements(), blogDTOs.getTotalPages(), blogDTOs.getTotalElements()))
                .status(ResponseStatus.SUCCESS)
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getBlogDetail(
            @PathVariable Long id
    ) {
        BlogDTO.BlogResponseDTO response = BlogDTO.BlogToBlogResponse(blogService.getBlogById(id));

        return ResponseEntity.ok(ResponseObject.builder()
                .data(response)
                .status(ResponseStatus.SUCCESS)
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @PutMapping("")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> updateBlogDetail(
            @Valid @RequestBody BlogRequest req
    ) {
        UserDetailsImpl u = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BlogDTO.BlogResponseDTO response = BlogDTO.BlogToBlogResponse(blogService.updateBlogById(req, u.getId()));

        return ResponseEntity.ok(ResponseObject.builder()
                .data(response)
                .status(ResponseStatus.SUCCESS)
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> deleteBlogDetail(
            @PathVariable Long id
    ) {
        blogService.deleteBlogById(id);
        return ResponseEntity.ok(ResponseObject.builder()
                .status(ResponseStatus.SUCCESS)
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .build());
    }

//    @GetMapping("/images/blogs-photo/{image}")
//    public void getBlogImages(HttpServletResponse httpServletResponse, @PathVariable String image) throws IOException {
////        String currentDir = new File("").getAbsolutePath();
////        InputStream imgFile = new ClassPathResource("/static/avatar.jpg").getInputStream();
//        String srcPath = System.getProperty("user.dir") + "\\images\\avatar.jpg";
////        URL url = getResource(srcPath);
//        InputStream in = url.openConnection().getInputStream();
//        httpServletResponse.getOutputStream().write(IOUtils.toByteArray(in));
//    }

}
