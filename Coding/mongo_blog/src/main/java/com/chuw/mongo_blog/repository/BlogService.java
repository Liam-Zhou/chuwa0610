package com.example.mongodb.service;

import com.example.mongodb.model.Blog;
import com.example.mongodb.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }
}