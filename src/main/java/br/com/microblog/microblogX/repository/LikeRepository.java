package br.com.microblog.microblogX.repository;

import br.com.microblog.microblogX.model.LikeModel;
import br.com.microblog.microblogX.model.PostModel;
import br.com.microblog.microblogX.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeModel, Long> {
    boolean existsByUserAndPost(UserModel user, PostModel post);
    Optional<LikeModel> findByUserAndPost(UserModel user, PostModel post);
    void deleteByUserAndPost(UserModel user, PostModel post);
    long countByPost(PostModel post);

}
