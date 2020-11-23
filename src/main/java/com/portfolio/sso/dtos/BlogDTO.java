package com.portfolio.sso.dtos;

import com.portfolio.sso.models.Blog;
import com.portfolio.sso.payload.response.BlogResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogDTO {
    public static BlogResponseDTO BlogToBlogResponse(Blog blog) {
        return BlogResponseDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .template(blog.getTemplate())
                .thumbnail(blog.getThumbnail())
                .body(blog.getBody())
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .banner(blog.getBanner())
                .published(blog
                        .getPublished()).build();
    }

    @Data
    @Builder
    public static class BlogResponseDTO {
        private Long id;
        private String title;
        private String template;
        private String thumbnail;
        private String body;
        private String banner;
        private Boolean published;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
