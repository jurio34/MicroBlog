package br.com.microblog.microblogX.dto;

import java.time.LocalDateTime;

public record PostResponse(Long id, String content, LocalDateTime createdAt, AuthorResponse author,
    long likesCount,
    boolean likedByCurrentUser) {

}
