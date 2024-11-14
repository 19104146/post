package com.usc.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeDTO {

    private Long id;
    private Long postId;
}
