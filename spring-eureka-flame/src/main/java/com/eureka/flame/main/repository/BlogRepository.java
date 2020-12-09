package com.eureka.flame.main.repository;

import com.eureka.flame.main.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
        @Query(value = "SELECT new Blog(b.id, b.title, b.template, b.thumbnail, b.body, b.banner, b.published, b.createdAt, b.updatedAt) " +
            "FROM Blog b JOIN User u ON u.id=b.user.id\n" +
            "WHERE UPPER(CONCAT(b.title, b.id, b.body)) LIKE UPPER(CONCAT('%', :keyword, '%'))\n" +
            "AND b.expirationTime>current_date\n" +
            "OR b.expirationTime is NULL")
//        @Query(value = "WITH publishedBlogs as (SELECT b.id, b.title, b.template, b.thumbnail, b.body, b.banner, b.published, b.createdAt, b.updatedAt\n" +
//                "FROM Blog b \n" +
//                "JOIN User u\n" +
//                "ON u.id=b.user.id\n" +
//                "WHERE b.expirationTime is NUll\n" +
//                "\tOR b.expirationTime>current_date)\n" +
//                "SELECT new com.portfolio.sso.payload.response.BlogResponse(id, title, template, thumbnail, body, banner,published, createdAt, updatedAt) \n" +
//                "From publishedBlogs\n" +
//                "WHERE CONCAT(title, id, body) LIKE '%?1%'")
//        @Query(value = "SELECT new com.portfolio.sso.payload.response.BlogResponse(b.id, b.title, b.template, b.thumbnail, b.body, b.banner, b.published, b.createdAt, b.updatedAt) \n"+
//                "FROM \n" +
//                "( SELECT b.id, b.title, b.template, b.thumbnail, b.body, b.banner, b.published, b.createdAt, b.updatedAt \n" +
//                "FROM Blog b \n" +
//                "JOIN User u\n" +
//                "ON u.id=b.user.id\n" +
//                "WHERE b.expirationTime is NUll\n" +
//                "OR b.expirationTime > current_date ) p\n" +
//                "WHERE UPPER(CONCAT(p.title, p.id, p.body)) LIKE UPPER(CONCAT('%', :keyword, '%'))")
    Page<Blog> getAllBlog(Pageable pageable,@Param("keyword") String keyword);
}