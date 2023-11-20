package com.example.quiz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;
import com.example.quiz.entity.Selection;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.service.ifs.QuizServer;
import com.example.quiz.vo.QuizReq;

@SpringBootTest
public class QuizServiceTest {

	@Autowired
	private QuizServer quizServer;

	@Test
	public void createQuizTest() {
		Quiz quiz = new Quiz(1, "民調", "到底是藍白合還是藍白拖", "已結束", LocalDate.of(2023, 11, 17), LocalDate.of(2023, 11, 20));
		List<Question> questions = new ArrayList<Question>();
		questions.add(new Question(1, 1, "猴柯配?", "radio"));
		questions.add(new Question(1, 2, "為啥", "textarea"));

		List<Selection> selections = new ArrayList<Selection>();
		selections.add(new Selection(1, 1, "科猴配", 0));
		selections.add(new Selection(1, 2, "猴科配", 0));
		selections.add(new Selection(1, 3, "藍白拖", 0));

		quizServer.createQuiz(new QuizReq(quiz, questions, selections));
	}

	@Test
	public void deleteAllTest() {
		quizServer.deleteQuiz();
	}
}
