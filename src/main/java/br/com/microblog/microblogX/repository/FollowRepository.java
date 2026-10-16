package br.com.microblog.microblogX.repository;

import br.com.microblog.microblogX.model.FollowModel;
import br.com.microblog.microblogX.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<FollowModel, Long> {
    boolean existsByFollowerAndFollowing(UserModel follower, UserModel following);
    Optional<FollowModel> findByFollowerAndFollowing(UserModel follower, UserModel following);
    void deleteByFollowerAndFollowing(UserModel follower, UserModel following);
    long countByFollowing(UserModel following);
    long countByFollower(UserModel follower);
}
