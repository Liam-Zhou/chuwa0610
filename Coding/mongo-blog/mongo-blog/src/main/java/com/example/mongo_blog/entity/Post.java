package com.example.mongo_blog.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Post")
public class Post {
    @Id
    private Long id;
    private String title;
    private String content;
    private String description;


    public Post(Long id, String title, String content, String description, LocalDateTime createDateTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
    }

    public Post() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
