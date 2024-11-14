package com.usc.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private int likeCount;
}
