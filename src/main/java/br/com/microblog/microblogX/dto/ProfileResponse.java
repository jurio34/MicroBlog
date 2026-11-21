package br.com.microblog.microblogX.dto;

public record ProfileResponse(
        Long id,
        String username,
        long followersCount,
        long followingCount,
        long postsCount,
        boolean followedByCurrentUser) {}
