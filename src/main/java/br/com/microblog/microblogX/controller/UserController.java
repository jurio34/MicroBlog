package br.com.microblog.microblogX.controller;

import br.com.microblog.microblogX.dto.FollowResponse;
import br.com.microblog.microblogX.dto.ProfileResponse;
import br.com.microblog.microblogX.security.JWTUserData;
import br.com.microblog.microblogX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponse> loadProfile(@PathVariable String username, @AuthenticationPrincipal JWTUserData user){
        Long currentUserId = (user != null) ? user.userId() : null;
        return ResponseEntity.ok(userService.getProfile(username, currentUserId));
    }
    @PostMapping("/{username}/follow")
    public ResponseEntity<FollowResponse> follow(@PathVariable String username, @AuthenticationPrincipal JWTUserData user){
        return ResponseEntity.ok(userService.followProfile(username, user.userId()));
    }
}
