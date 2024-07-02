package com.jackpang.mongo_blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * description: Post
 * date: 6/28/24 9:48 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Document("Posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    private String description;

}
