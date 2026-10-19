package br.com.microblog.microblogX.security;



import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.microblog.microblogX.model.UserModel;

@Component 
public class TokenConfig {
    
    private String secret = "secret";


    public String generateToken(UserModel user){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
        .withClaim("userId", user.getId())
        .withSubject(user.getUsername())
        .withExpiresAt(Instant.now().plusSeconds(68400))
        .withIssuedAt(Instant.now())
        .sign(algorithm);
    }
}
