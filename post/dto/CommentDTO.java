package com.usc.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private String content;
    private Long postId;
}
