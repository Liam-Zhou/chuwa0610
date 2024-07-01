package com.chuwa.xhs.service.Impl;

import com.chuwa.xhs.dao.PostRepository;
import com.chuwa.xhs.entity.Post;
import com.chuwa.xhs.payload.PostDto;
import com.chuwa.xhs.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //step 1: create object "Post"
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        //step 2: connect to DB and save object "Post"

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Post savedPost = postRepository.save(post);
        PostDto response = new PostDto();
        response.setId(savedPost.getId());
        response.setTitle(savedPost.getTitle());
        response.setContent(savedPost.getContent());
        response.setDescription(savedPost.getDescription());

        return response;
    }
}
