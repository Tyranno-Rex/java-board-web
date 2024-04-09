package com.example.sbb2;

import java.time.LocalDateTime;
import com.example.sbb2.Question.Question;
import com.example.sbb2.Question.QuestionRepository;
import com.example.sbb2.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@SpringBootTest
class Sbb2ApplicationTests {
	@Autowired // autowired는 스프링이 관리하는 객체를 주입받는다.
	private QuestionService questionS;
	@Test
	void TestJap() {

		for (int i = 0; i < 40; i++) {
			String subject = "제목" + i;
			String content = "내용" + i;
			this.questionS.create(subject, content);
		}
	}

}
