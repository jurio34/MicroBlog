package br.com.microblog.microblogX.service;


import br.com.microblog.microblogX.dto.PostRequest;
import br.com.microblog.microblogX.dto.PostResponse;
import br.com.microblog.microblogX.model.PostModel;
import br.com.microblog.microblogX.model.UserModel;
import br.com.microblog.microblogX.repository.PostRepository;
import br.com.microblog.microblogX.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("Deve criar e retornar o post com sucesso")
    void createSuccess() {
        String username = "fulano";
        Long userId = 1L;
        PostRequest req = new PostRequest("Teste de conteudo");

        UserModel author = new UserModel();
        author.setId(userId);
        author.setUsername(username);

        PostModel savedPost = new PostModel();
        savedPost.setId(10L);
        savedPost.setAuthor(author);
        savedPost.setContent(req.content());


        when(userRepository.findById(userId)).thenReturn(Optional.of(author));
        when(postRepository.save(any())).thenReturn(savedPost);

        PostResponse res = postService.create(userId, req);

        Assertions.assertNotNull(res);
        verify(userRepository).findById(userId);
        verify(postRepository).save(any());
    }
}
