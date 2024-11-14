package com.usc.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostCreateDTO {

    private String title;
    private String content;
}
