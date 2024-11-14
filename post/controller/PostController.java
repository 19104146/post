package com.usc.post.controller;

import com.usc.post.dto.*;
import com.usc.post.service.CommentService;
import com.usc.post.service.LikeService;
import com.usc.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;

    @Autowired
    public PostController(PostService postService, CommentService commentService, LikeService likeService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> listPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateDTO postCreateDTO) {
        PostDTO createdPost = postService.create(postCreateDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDTO> commentOnPost(@RequestBody CommentCreateDTO commentCreateDTO) {
        CommentDTO createdComment = commentService.create(commentCreateDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdComment.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdComment);
    }

    @PostMapping("/likes")
    public ResponseEntity<LikeDTO> likePost(@RequestBody LikeCreateDTO likeCreateDTO) {
        LikeDTO createdLike = likeService.create(likeCreateDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLike.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdLike);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Long postId) {
        List<CommentDTO> comments = commentService.findAllByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<LikeDTO>> getLikesByPostId(@PathVariable Long postId) {
        List<LikeDTO> likes = likeService.findAllByPostId(postId);
        return ResponseEntity.ok(likes);
    }
}
