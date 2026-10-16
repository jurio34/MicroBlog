package br.com.microblog.microblogX.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "follows", uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "following_id"}))
public class FollowModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private UserModel follower;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private UserModel following;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
