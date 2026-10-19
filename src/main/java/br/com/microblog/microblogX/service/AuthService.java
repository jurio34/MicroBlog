package br.com.microblog.microblogX.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.microblog.microblogX.dto.LoginRequest;
import br.com.microblog.microblogX.dto.LoginResponse;
import br.com.microblog.microblogX.dto.RegisterRequest;
import br.com.microblog.microblogX.dto.RegisterResponse;
import br.com.microblog.microblogX.model.UserModel;
import br.com.microblog.microblogX.repository.UserRepository;
import br.com.microblog.microblogX.security.TokenConfig;


@Service 
 
public class AuthService {
    @Autowired 
    private UserRepository userRepository;
   
    @Autowired 
    private AuthenticationManager authenticationManager;
    
    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired 
    private TokenConfig tokenConfig;

    public RegisterResponse register(RegisterRequest request){
        if(userRepository.existsByUsername(request.username())){
            throw new ResponseStatusException (HttpStatus.CONFLICT, "username já está em uso!");
        }
        if(userRepository.existsByEmail(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email já está em uso");
        }
        UserModel user = new UserModel();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        UserModel saved = userRepository.save(user);
        return new RegisterResponse(saved.getId(), saved.getUsername(), saved.getEmail());
    }
    public LoginResponse login(LoginRequest request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserModel user = userRepository.findByUsername(request.username())
            .orElseThrow(() -> new BadCredentialsException("usuário ou senha inválidos"));

        String token = tokenConfig.generateToken(user);
        return new LoginResponse(token);
    }
}
