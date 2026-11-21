package br.com.microblog.microblogX.dto;

public record FollowResponse(
        long followersCount,
        boolean followedByCurrentUser) {}
