package br.com.microblog.microblogX.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(@NotBlank  @Size (max = 280) String content) {

}
