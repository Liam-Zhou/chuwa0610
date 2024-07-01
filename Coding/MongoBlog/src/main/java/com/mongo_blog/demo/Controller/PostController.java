package com.mongo_blog.demo.Controller;

import com.mongo_blog.demo.payload.PostDto;
import com.mongo_blog.demo.entity.Post;
import com.mongo_blog.demo.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto) {
        if (postDto.getTitle() == null || postDto.getContent() == null || postDto.getDescription() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Post post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
