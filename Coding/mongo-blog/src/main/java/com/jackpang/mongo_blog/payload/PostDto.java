package com.jackpang.mongo_blog.payload;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: PostDto
 * date: 6/28/24 10:04 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Resource
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String description;
}
