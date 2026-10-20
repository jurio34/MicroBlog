package br.com.microblog.microblogX.security;

import lombok.Builder;

@Builder 
public record JWTUserData(Long userId, String username) {

}
