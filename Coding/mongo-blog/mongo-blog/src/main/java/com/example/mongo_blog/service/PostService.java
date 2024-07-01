package com.example.mongo_blog.service;

import com.example.mongo_blog.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
