package com.example.mongo_blog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Post {
    @Id
    private String id;
    private String title;
    private String content;

    // Constructors, Getters, and Setters
    public Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}

