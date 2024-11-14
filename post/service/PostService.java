package com.usc.post.service;

import com.usc.post.dto.PostCreateDTO;
import com.usc.post.dto.PostDTO;
import com.usc.post.entity.Post;
import com.usc.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> findAll() {
        return postRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PostDTO findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.map(post -> toDTO(postRepository.save(post))).orElse(null);
    }

    public PostDTO create(PostCreateDTO postCreateDTO) {
        Post post = new Post(postCreateDTO.getTitle(), postCreateDTO.getContent());
        return toDTO(postRepository.save(post));
    }

    private PostDTO toDTO(Post post) {
        return new PostDTO(post.getId(), post.getContent(), post.getTitle(), post.getLikes().size());
    }
}
