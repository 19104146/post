package com.usc.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LikeCreateDTO {

    private Long postId;

    public LikeCreateDTO(@JsonProperty("postId") Long postId) {
        this.postId = postId;
    }
}
