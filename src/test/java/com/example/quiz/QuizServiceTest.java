package com.example.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuestionDao;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;
import com.example.quiz.vo.QuizSearchRes;

@SpringBootTest
public class QuizServiceTest {

	@Autowired
	private QuizService quizService;

	@Test
	public void createQuizOnly() {
		Quiz quiz = new Quiz();

		// 只有問卷
		quiz = new Quiz("測試只有問卷", null, "尚未開始", null, null);
		QuizRes res = quizService.createQuiz(new QuizReq(quiz));

		System.out.println(res.getRtnCode().getMessage());
	}

	@Test
	public void createAll() {
		// 創建一個問卷對象
		Quiz quiz = new Quiz("吃什麼東西好哩?", null, "尚未開始", null, null);

		// 先保存問卷到數據庫
		QuizRes quizRes = quizService.createQuiz(new QuizReq(quiz));
		assertEquals(RtnCode.SUCCESSFUL, quizRes.getRtnCode()); // 確保問卷保存成功
		quiz = quizRes.getQuiz(); // 獲取保存到數據庫後的問卷對象

		// 使用已保存的問卷ID創建問題
		List<Question> questions = new ArrayList<>();
		questions.add(new Question(quiz.getId(), "早餐吃什麼?", null, null));
		questions.add(new Question(quiz.getId(), "午餐吃什麼?", null, null));
		questions.add(new Question(quiz.getId(), "消夜吃什麼?", null, null));

		// 將問題保存到數據庫
		QuizRes res = quizService.createQuiz(new QuizReq(quiz, questions));

		assertEquals(RtnCode.SUCCESSFUL, res.getRtnCode()); // 確保問題保存成功
		assertNotNull(res.getQuiz()); // 確保返回的 Quiz 對象不為空

		System.out.println(res.getRtnCode().getMessage());
	}

	@Test
	public void createAll2() {
		// 創建一個問卷對象
		Quiz quiz = new Quiz("人氣VTuber投票", null, "已結束", null, null);

		// 先保存問卷到數據庫
		QuizRes quizRes = quizService.createQuiz(new QuizReq(quiz));
		assertEquals(RtnCode.SUCCESSFUL, quizRes.getRtnCode()); // 確保問卷保存成功
		quiz = quizRes.getQuiz(); // 獲取保存到數據庫後的問卷對象

		// 使用已保存的問卷ID創建問題
		List<Question> questions = new ArrayList<>();
		questions.add(new Question(quiz.getId(), "你最喜歡的Vtuber企業?", null, "cover;彩虹社;vspo"));
		questions.add(new Question(quiz.getId(), "你最喜歡的Vtuber?", null, "亞周統什;拖椅子;九面人;阿什"));
		questions.add(new Question(quiz.getId(), "為什麼?", null, null));

		// 將問題保存到數據庫
		QuizRes res = quizService.createQuiz(new QuizReq(quiz, questions));

		assertEquals(RtnCode.SUCCESSFUL, res.getRtnCode()); // 確保問題保存成功
		assertNotNull(res.getQuiz()); // 確保返回的 Quiz 對象不為空

		System.out.println(res.getRtnCode().getMessage());
	}

	@Test
	public void searchQuizTest() {
		// null
		System.out.println("-----沒有條件-----");
		QuizSearchRes res = quizService.searchQuiz(null, null);
		List<Quiz> quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getId());
		}

		// 只有title
		System.out.println("-----只有title-----");
		res = quizService.searchQuiz("1", null);
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getId());
		}

		// 只有state
		System.out.println("-----只有state-----");
		res = quizService.searchQuiz(null, "尚未開始");
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getId());
		}

		// 兩個條件
		System.out.println("-----兩個條件都有-----");
		res = quizService.searchQuiz("1", "尚未開始");
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getId());
		}
	}

	@Test
	public void getQuizInfoTest() {
		// 不存在的id
		quizService.getQuizInfo(0);
		// 存在的id
		quizService.getQuizInfo(2);
	}

	@Test
	public void deleteQuizTest() {
		// 不存在quizid
		System.out.println("-----不存在的quizid-----");
		quizService.deleteQuiz(0);
		// 存在qid(有question & selection)
		System.out.println("-----存在的quizid-----");
		quizService.deleteQuiz(12);
	}

	@Autowired
	private QuizDao quizDao;

	@Autowired
	private QuestionDao questionDao;

	@Test
	public void deleteAll() {
		quizDao.deleteAll();
		questionDao.deleteAll();
	}
}
