package br.com.microblog.microblogX.repository;

import br.com.microblog.microblogX.model.PostModel;
import br.com.microblog.microblogX.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<PostModel, Long> {
    Page<PostModel> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<PostModel> findByAuthorOrderByCreatedAtDesc(UserModel author, Pageable pageable);

    @Query("""
            SELECT p FROM PostModel p
                    WHERE p.author.id IN (
                        SELECT f.following.id FROM FollowModel f WHERE f.follower.id = :userId
                    )
                    ORDER BY p.createdAt DESC
            """)
    Page<PostModel> findFeedForUser(@Param("userId") Long userId, Pageable pageable);
}
