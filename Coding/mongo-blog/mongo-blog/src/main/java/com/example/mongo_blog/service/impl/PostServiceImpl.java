package com.example.mongo_blog.service.impl;

import com.example.mongo_blog.dao.PostRepository;
import com.example.mongo_blog.entity.Post;
import com.example.mongo_blog.payload.PostDto;
import com.example.mongo_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        //step 1: create object "Post"
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        //step 2: connect to DB and save object "Post"
        Post savedPost = postRepository.save(post);
        PostDto response = new PostDto();
        response.setId(savedPost.getId());
        response.setTitle(savedPost.getTitle());
        response.setContent(savedPost.getContent());
        response.setDescription(savedPost.getDescription());
        return response;
    }
}
