package com.usc.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentCreateDTO {

    private String content;
    private Long postId;
}
