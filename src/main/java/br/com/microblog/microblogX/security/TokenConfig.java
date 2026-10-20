package br.com.microblog.microblogX.security;



import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

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
    public Optional<JWTUserData> validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT decode = JWT.require(algorithm)
                    .build().verify(token);
        return Optional.of(JWTUserData.builder()
                .userId(decode.getClaim("userId").asLong())
                .username(decode.getSubject())
                .build());
                    
        }catch(JWTVerificationException ex){
            return Optional.empty();
        }
    }
}
