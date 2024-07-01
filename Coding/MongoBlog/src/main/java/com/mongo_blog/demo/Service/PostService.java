package com.mongo_blog.demo.Service;

import com.mongo_blog.demo.payload.PostDto;
import com.mongo_blog.demo.entity.Post;

public interface PostService {
    Post createPost(PostDto postDto);
}
