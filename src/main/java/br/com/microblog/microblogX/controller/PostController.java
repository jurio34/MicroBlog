package br.com.microblog.microblogX.controller;

import br.com.microblog.microblogX.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microblog.microblogX.dto.PostRequest;
import br.com.microblog.microblogX.dto.PostResponse;
import br.com.microblog.microblogX.security.JWTUserData;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping ("/api/posts")
public class PostController {

    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@AuthenticationPrincipal JWTUserData user, @Valid @RequestBody PostRequest request){
         return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(user.userId(), request));
    }
}
