package br.com.microblog.microblogX.service;

import br.com.microblog.microblogX.dto.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.microblog.microblogX.dto.AuthorResponse;
import br.com.microblog.microblogX.dto.PostRequest;
import br.com.microblog.microblogX.dto.PostResponse;
import br.com.microblog.microblogX.model.PostModel;
import br.com.microblog.microblogX.model.UserModel;
import br.com.microblog.microblogX.repository.PostRepository;
import br.com.microblog.microblogX.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PostResponse create(Long userId, PostRequest request){
        UserModel author = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "usuário não encontrado"));
        PostModel post = new PostModel();
        post.setAuthor(author);
        post.setContent(request.content());

        PostModel saved = postRepository.save(post);
        return toResponse(saved);
    }

    private PostResponse toResponse(PostModel post) {
        return new PostResponse(
                post.getId(),
                post.getContent(),
                post.getCreatedAt(),
                new AuthorResponse(post.getAuthor().getId(), post.getAuthor().getUsername()),
                0,
                false);
    }
    public PagedResponse<PostResponse> feed(Pageable pageable){
        Page<PostModel> posts = postRepository.findAllByOrderByCreatedAtDesc(pageable);
        return PagedResponse.from(posts.map(this::toResponse));
    }

}



