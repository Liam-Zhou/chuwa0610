package com.chuwa.redbook.service.Implimentation;

import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PostServiceImplimentation implements PostService {


    private PostRepository postRepository;

    public PostServiceImplimentation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(@RequestBody PostDto postDto) {
        // step1 create object "Post"
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        // step connect ro DB and save object "Post"
        Post savedPost = postRepository.save(post);
        PostDto responce = new PostDto();
        responce.setTitle(savedPost.getTitle());
        responce.setContent(savedPost.getContent());
        responce.setDescription(savedPost.getDescription());
        responce.setId(savedPost.getId());

        return responce;
    }
}
