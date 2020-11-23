package com.portfolio.sso.models;

import com.portfolio.sso.payload.request.BlogRequest;
import com.portfolio.sso.payload.response.BlogResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private String title;

    @NotBlank
    private String template;

    @NotBlank
    private String thumbnail;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String body;

    private String banner;

    @NotBlank
    private Boolean published;

    @NotBlank
    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotBlank
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotBlank
    private LocalDateTime expirationTime;

    public Blog(@NotBlank Long id, @NotBlank User user, @NotBlank String title, @NotBlank String template, @NotBlank String thumbnail, @NotBlank String body, String banner, @NotBlank Boolean published,@NotBlank LocalDateTime expirationTime) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.template = template;
        this.thumbnail = thumbnail;
        this.body = body;
        this.banner = banner;
        this.published = published;
        this.expirationTime =  expirationTime;
    }

    public Blog(@NotBlank Long id, @NotBlank String title, @NotBlank String template, @NotBlank String thumbnail, @NotBlank String body, String banner, @NotBlank Boolean published, @NotBlank LocalDateTime createdAt,@NotBlank LocalDateTime updatedAt){
        this.id = id;
        this.title = title;
        this.template = template;
        this.thumbnail = thumbnail;
        this.body = body;
        this.banner = banner;
        this.published = published;
        this.createdAt =  createdAt;
        this.updatedAt = updatedAt;
    }

    public Blog() {
    }

    public Blog(BlogRequest req, User user) {
        if (req.getBanner() == null || req.getBanner().isEmpty()) {
            this.banner = req.getThumbnail();
        } else {
            this.banner = req.getBanner();
        }
        this.title = req.getTitle();
        this.template = req.getTemplate();
        this.user = user;
        this.thumbnail = req.getThumbnail();
        this.body = req.getBody();
        this.published = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public BlogResponse toResponse() {
        BlogResponse res =  new BlogResponse();
        res.setId(this.id);
        res.setTitle(this.title);
        res.setTemplate(this.template);
        res.setThumbnail(this.thumbnail);
        res.setBody(this.body);
        res.setCreatedAt(this.createdAt);
        res.setUpdatedAt(this.updatedAt);
        res.setBanner(this.banner);
        res.setPublished(this.published);
        return res;
    }
}
