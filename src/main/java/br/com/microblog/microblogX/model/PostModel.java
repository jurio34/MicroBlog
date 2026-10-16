package br.com.microblog.microblogX.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "post")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 260)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel author;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
