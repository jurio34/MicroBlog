package br.com.microblog.microblogX.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank String username, @NotBlank String password
) {}
