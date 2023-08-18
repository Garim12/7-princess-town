package com.example.princesstown.entity;

import com.example.princesstown.dto.comment.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicInsert
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private Long likeCnt;

    @Column
    private String emoji;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_userId")
    private User user;

    @OneToMany( mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Reply> ReplyList= new ArrayList<>();

    public Comment(CommentRequestDto requestDto, Post post, User user) {
        this.id = getId();
        this.content = requestDto.getContent();
        this.likeCnt = getLikeCnt();
        this.post = post;
        this.user = user;
    }
}
