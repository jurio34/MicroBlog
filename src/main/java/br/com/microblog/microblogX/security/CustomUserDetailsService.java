package br.com.microblog.microblogX.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.microblog.microblogX.model.UserModel;
import br.com.microblog.microblogX.repository.UserRepository;


@Service

public class CustomUserDetailsService implements UserDetailsService{
   @Autowired 
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username)  {
        UserModel user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new User(user.getUsername(), user.getPasswordHash(), List.of());
       
    }


}
