package com.usc.post.service;

import com.usc.post.dto.LikeCreateDTO;
import com.usc.post.dto.LikeDTO;
import com.usc.post.entity.Like;
import com.usc.post.entity.Post;
import com.usc.post.repository.LikeRepository;
import com.usc.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    public List<LikeDTO> findAllByPostId(Long postId) {
        List<Like> likes = likeRepository.findAllByPostId(postId);
        return likes.stream()
                .map(like -> new LikeDTO(like.getId(), like.getPost().getId()))
                .collect(Collectors.toList());
    }

    public LikeDTO create(LikeCreateDTO likeCreateDTO) {
        Optional<Post> optionalPost = postRepository.findById(likeCreateDTO.getPostId());
        return optionalPost.map(post -> toDTO(likeRepository.save(new Like(post)))).orElse(null);
    }

    private LikeDTO toDTO(Like like) {
        return new LikeDTO(like.getId(), like.getPost().getId());
    }
}
