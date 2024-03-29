package com.mysite.sbb.comment;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query ("select distinct c from Comment c where c.author = :author")
    Page<Comment> findByAuthor(SiteUser author, Pageable pageable);
}