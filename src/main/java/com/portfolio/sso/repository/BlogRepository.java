package com.portfolio.sso.repository;

import com.portfolio.sso.models.*;
import com.portfolio.sso.payload.response.CreateBlogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query(value = "SELECT new com.portfolio.sso.payload.response.CreateBlogResponse(b.id, b.title, b.template, b.thumbnail, b.body, b.banner, b.published, b.createdAt, b.updatedAt) FROM Blog b JOIN User u ON u.id=b.user.id")
    Page<CreateBlogResponse> getAllBlog(Pageable pageable);
}