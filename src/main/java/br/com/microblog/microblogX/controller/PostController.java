package br.com.microblog.microblogX.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microblog.microblogX.dto.PostRequest;
import br.com.microblog.microblogX.dto.PostResponse;
import br.com.microblog.microblogX.security.JWTUserData;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/api/posts")
public class PostController {

    public ResponseEntity<PostResponse> create(@AuthenticationPrincipal@Valid @RequestBody  JWTUserData user, PostRequest request){
            return null;
    }
}
