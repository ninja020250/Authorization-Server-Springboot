package com.portfolio.sso.repository;

import com.portfolio.sso.models.Blog;
import com.portfolio.sso.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
