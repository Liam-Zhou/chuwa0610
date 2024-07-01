package com.example.mongo_blog.dao;

import com.example.mongo_blog.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
