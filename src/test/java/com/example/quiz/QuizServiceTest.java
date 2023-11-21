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
import com.example.quiz.repository.QuestionDao;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.repository.SelectionDao;
import com.example.quiz.service.ifs.QuizServer;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizSearchRes;

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

		quiz = new Quiz(3, "滿意度調查", "填寫餐廳問卷滿意度", "發布中", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 31));
		questions = new ArrayList<Question>();
		questions.add(new Question(3, 6, "今天用餐滿意度?", "radio"));
		questions.add(new Question(3, 7, "更多意見", "textarea"));

		selections = new ArrayList<Selection>();
		selections.add(new Selection(6, 13, "非常滿意", 25));
		selections.add(new Selection(6, 14, "普通", 0));
		selections.add(new Selection(6, 15, "非常不滿意", 0));

		quizServer.createQuiz(new QuizReq(quiz, questions, selections));

		quiz = new Quiz(4, "人氣主播票選", "選出2023最受歡迎的主播", "尚未開始", LocalDate.of(2023, 12, 25), LocalDate.of(2023, 12, 31));
		questions = new ArrayList<Question>();
		questions.add(new Question(4, 8, "誰是你最喜歡的主播", "checkbox"));
		questions.add(new Question(4, 9, "為啥", "textarea"));
		questions.add(new Question(4, 10, "想對他說的話", "textarea"));

		selections = new ArrayList<Selection>();
		selections.add(new Selection(8, 16, "嘎痛", 0));
		selections.add(new Selection(8, 17, "陌生人", 0));
		selections.add(new Selection(8, 18, "拖椅子", 0));
		selections.add(new Selection(8, 19, "館長", 0));

		quizServer.createQuiz(new QuizReq(quiz, questions, selections));
	}

	@Test
	public void searchQuizTest() {
		QuizSearchRes res = quizServer.searchQuiz("1");
		List<Quiz> quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}
	}

	@Test
	public void deleteSeleTest() {
		// 不存在seleid
		quizServer.deleteSelection(0);
		// 存在seleid
		quizServer.deleteSelection(11);
	}

	@Test
	public void deleteQuesTest() {
		// 不存在qid
		quizServer.deleteQuestion(0);
		// 存在qid(有選項)
		quizServer.deleteQuestion(1);
		// 存在qid
		quizServer.deleteQuestion(2);
	}

	@Test
	public void deleteQuizTest() {
		// 不存在qid
		quizServer.deleteQuiz(0);
		// 存在qid(有question & selection)
		quizServer.deleteQuiz(4);
	}

}
