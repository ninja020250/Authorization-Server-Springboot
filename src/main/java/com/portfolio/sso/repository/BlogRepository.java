package com.portfolio.sso.repository;

import com.portfolio.sso.models.*;
import com.portfolio.sso.payload.response.BlogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query(value = "SELECT new com.portfolio.sso.payload.response.BlogResponse(b.id, b.title, b.template, b.thumbnail, b.body, b.banner, b.published, b.createdAt, b.updatedAt) " +
            "FROM Blog b JOIN User u ON u.id=b.user.id\n" +
            "WHERE CONCAT(b.title, b.id, b.body) LIKE %?1%\n" +
            "AND b.expirationTime>current_date\n" +
            "OR b.expirationTime is NULL")
    Page<BlogResponse> getAllBlog(Pageable pageable, String keyword);
}