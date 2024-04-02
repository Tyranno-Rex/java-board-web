package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import jakarta.persistence.ManyToMany;
import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.comment.Comment;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @ManyToMany
    Set<SiteUser> viewer;

    private LocalDateTime modifyDate;
    private String category;
    private LocalDateTime createDate;
}
