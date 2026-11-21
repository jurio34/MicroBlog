package br.com.microblog.microblogX.service;

import br.com.microblog.microblogX.dto.FollowResponse;
import br.com.microblog.microblogX.dto.ProfileResponse;
import br.com.microblog.microblogX.model.FollowModel;
import br.com.microblog.microblogX.model.UserModel;
import br.com.microblog.microblogX.repository.FollowRepository;
import br.com.microblog.microblogX.repository.PostRepository;
import br.com.microblog.microblogX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private PostRepository postRepository;

    public ProfileResponse getProfile(String username, Long currentUserId){
        UserModel alvo = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado"));


        long followers = followRepository.countByFollowing(alvo);
        long following = followRepository.countByFollower(alvo);
        long posts = postRepository.countByAuthor(alvo);

        boolean followedByCurrentUser = false;

        if (currentUserId != null && !currentUserId.equals(alvo.getId())) {
            UserModel atual = userRepository.findById(currentUserId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "usuário não encontrado"));
            followedByCurrentUser = followRepository.existsByFollowerAndFollowing(atual, alvo);
        }

        return new ProfileResponse(alvo.getId(), alvo.getUsername(), followers, following, posts, followedByCurrentUser);

    }

    public FollowResponse followProfile(String username, Long currentUserId){
        UserModel alvo = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado"));
        UserModel atual = userRepository.findById(currentUserId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "usuário não encontrado"));

        if(alvo.getId().equals(atual.getId())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "você não pode seguir a si mesmo");
        }
        if(followRepository.existsByFollowerAndFollowing(atual, alvo)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "você já segue este usuário");
        }

        FollowModel follow = new FollowModel();
        follow.setFollower(atual);
        follow.setFollowing(alvo);
        followRepository.save(follow);

        long followers = followRepository.countByFollowing(alvo);

        return new FollowResponse(followers, true);

    }


}
