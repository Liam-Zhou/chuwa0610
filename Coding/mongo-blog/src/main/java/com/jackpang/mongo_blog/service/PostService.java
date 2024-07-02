package com.jackpang.mongo_blog.service;

import com.jackpang.mongo_blog.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
