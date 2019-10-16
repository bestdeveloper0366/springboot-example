package com.example.board.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(length = 20, nullable = false)
    private String author;

    @CreationTimestamp
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)        //fetch = FetchType.EAGER 공부하기
    private List<Comment> comments = new ArrayList<>();

    public void addComments(Comment comment) {
        this.comments.add(comment);
    }
}
