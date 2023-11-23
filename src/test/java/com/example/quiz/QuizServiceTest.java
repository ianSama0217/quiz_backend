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
import com.example.quiz.entity.Selection;
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
	public void createQuizAndQuestion() {
	    // 創建一個問卷對象
	    Quiz quiz = new Quiz("吃什麼東西好哩?", null, "尚未開始", null, null);

	    // 先保存問卷到數據庫
	    QuizRes quizRes = quizService.createQuiz(new QuizReq(quiz));
	    assertEquals(RtnCode.SUCCESSFUL, quizRes.getRtnCode()); // 確保問卷保存成功
	    quiz = quizRes.getQuiz(); // 獲取保存到數據庫後的問卷對象

	    // 使用已保存的問卷ID創建問題
	    List<Question> questions = new ArrayList<>();
	    questions.add(new Question(quiz.getId(), "早餐吃什麼?", null));
	    questions.add(new Question(quiz.getId(), "午餐吃什麼?", null));
	    questions.add(new Question(quiz.getId(), "消夜吃什麼?", null));

	    // 將問題保存到數據庫
	    QuizRes res = quizService.createQuiz(new QuizReq(quiz, questions));

	    assertEquals(RtnCode.SUCCESSFUL, res.getRtnCode()); // 確保問題保存成功
	    assertNotNull(res.getQuiz()); // 確保返回的 Quiz 對象不為空

	    System.out.println(res.getRtnCode().getMessage());
	}



	@Test
	public void createAll() {
		int lastIndex;
		Quiz quiz = new Quiz();
		List<Question> questions = new ArrayList<Question>();
		List<Selection> selections = new ArrayList<Selection>();

//		// 問卷+問題+選項
//		quiz = new Quiz("測試問卷+問題+選項", null, "尚未開始", null, null);
//		questions.add(new Question(quiz.getId(), "最喜歡的vtuber?", null));
//		// 取得陣列最後一個值
//		lastIndex = questions.size() - 1;
//		int qid = Integer.parseInt(questions.get(lastIndex));
//		selections.add(new Selection( 1, "百鬼", 0));
//
//		questions.add(new Question(quiz.getId(), "最想去的國家?", null));
	}

	@Test
	public void searchQuizTest() {
		// null
		System.out.println("-----沒有條件-----");
		QuizSearchRes res = quizService.searchQuiz(null, null);
		List<Quiz> quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}

		// 只有title
		System.out.println("-----只有title-----");
		res = quizService.searchQuiz("調", null);
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}

		// 只有state
		System.out.println("-----只有state-----");
		res = quizService.searchQuiz(null, "尚未開始");
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}

		// 兩個條件
		System.out.println("-----兩個條件都有-----");
		res = quizService.searchQuiz("調", "發布中");
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
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
	public void deleteSeleTest() {
		// 不存在seleid
		System.out.println("-----不存在的seleid-----");
		quizService.deleteSelection(0);
		// 存在seleid
		System.out.println("-----存在的seleid-----");
		quizService.deleteSelection(11);
	}

	@Test
	public void deleteQuesTest() {
		// 不存在qid
		System.out.println("-----不存在的qid-----");
		quizService.deleteQuestion(0);
		// 存在qid(有選項)
		System.out.println("-----存在的qid(有選像)-----");
		quizService.deleteQuestion(1);
		// 存在qid
		System.out.println("-----存在的qid-----");
		quizService.deleteQuestion(2);
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

}
