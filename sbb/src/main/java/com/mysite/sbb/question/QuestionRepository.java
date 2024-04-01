package com.mysite.sbb.question;

import java.util.List;

import com.mysite.sbb.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);

    Page<Question> findAll(Specification<Question> spec, Pageable pageable);


    @Query("select "
            + "distinct q "
            + "from Question q "
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

    @Query(value = "select q.id " +
            "from question q " +
            "left join question_voter qv on qv.question_id = q.id " +
            "group by q.id " +
            "order by count(qv.question_id) desc", nativeQuery = true)
    Page<Integer> findAllByKeywordOrderByLike(@Param("kw") String keyword, Pageable pageable);

    @Query("select distinct q " +
            "from Question q " +
            "left outer join q.author u1 " +
            "left outer join Answer a on a.question = q " +
            "left outer join a.author u2 " +
            "where u1 = :author")
    Page<Question> findByAuthor(SiteUser author, Pageable pageable);
}


