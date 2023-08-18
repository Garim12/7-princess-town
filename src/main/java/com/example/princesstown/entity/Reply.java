package com.example.princesstown.entity;

import com.example.princesstown.dto.comment.CommentRequestDto;
import com.example.princesstown.dto.comment.ReplyRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@Entity
@DynamicInsert
@NoArgsConstructor
@Table(name = "replys")
public class Reply extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column
    private String emoji;

    @ColumnDefault("0")
    private Long likeCnt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_userId")
    private User user;

    public Reply(ReplyRequestDto requestDto, Post post, Comment comment, User user) {
        this.id = getId();
        this.content = requestDto.getContent();
        this.likeCnt = getLikeCnt();
        this.post = post;
        this.comment = comment;
        this.user = user;
    }
}
