package br.com.microblog.microblogX.dto;

public record LoginResponse(
    String token, long expiresIn
) {}
