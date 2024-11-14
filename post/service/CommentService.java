package com.usc.post.service;

import com.usc.post.dto.CommentCreateDTO;
import com.usc.post.dto.CommentDTO;
import com.usc.post.entity.Comment;
import com.usc.post.entity.Post;
import com.usc.post.repository.CommentRepository;
import com.usc.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<CommentDTO> findAllByPostId(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream()
                .map(comment -> new CommentDTO(comment.getId(), comment.getContent(), comment.getPost().getId()))
                .collect(Collectors.toList());
    }

    public CommentDTO create(CommentCreateDTO commentCreateDTO) {
        Optional<Post> optionalPost = postRepository.findById(commentCreateDTO.getPostId());
        return optionalPost.map(post -> toDTO(commentRepository.save(new Comment(commentCreateDTO.getContent(), post)))).orElse(null);
    }

    private CommentDTO toDTO(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getContent(), comment.getPost().getId());
    }
}
