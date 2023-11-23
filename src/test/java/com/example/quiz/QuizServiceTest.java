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

		// �u���ݨ�
		quiz = new Quiz("���եu���ݨ�", null, "�|���}�l", null, null);
		QuizRes res = quizService.createQuiz(new QuizReq(quiz));
		
		System.out.println(res.getRtnCode().getMessage());
	}

	@Test
	public void createQuizAndQuestion() {
	    // �Ыؤ@�Ӱݨ���H
	    Quiz quiz = new Quiz("�Y����F��n��?", null, "�|���}�l", null, null);

	    // ���O�s�ݨ���ƾڮw
	    QuizRes quizRes = quizService.createQuiz(new QuizReq(quiz));
	    assertEquals(RtnCode.SUCCESSFUL, quizRes.getRtnCode()); // �T�O�ݨ��O�s���\
	    quiz = quizRes.getQuiz(); // ����O�s��ƾڮw�᪺�ݨ���H

	    // �ϥΤw�O�s���ݨ�ID�Ыذ��D
	    List<Question> questions = new ArrayList<>();
	    questions.add(new Question(quiz.getId(), "���\�Y����?", null));
	    questions.add(new Question(quiz.getId(), "���\�Y����?", null));
	    questions.add(new Question(quiz.getId(), "���]�Y����?", null));

	    // �N���D�O�s��ƾڮw
	    QuizRes res = quizService.createQuiz(new QuizReq(quiz, questions));

	    assertEquals(RtnCode.SUCCESSFUL, res.getRtnCode()); // �T�O���D�O�s���\
	    assertNotNull(res.getQuiz()); // �T�O��^�� Quiz ��H������

	    System.out.println(res.getRtnCode().getMessage());
	}



	@Test
	public void createAll() {
		int lastIndex;
		Quiz quiz = new Quiz();
		List<Question> questions = new ArrayList<Question>();
		List<Selection> selections = new ArrayList<Selection>();

//		// �ݨ�+���D+�ﶵ
//		quiz = new Quiz("���հݨ�+���D+�ﶵ", null, "�|���}�l", null, null);
//		questions.add(new Question(quiz.getId(), "�̳��w��vtuber?", null));
//		// ���o�}�C�̫�@�ӭ�
//		lastIndex = questions.size() - 1;
//		int qid = Integer.parseInt(questions.get(lastIndex));
//		selections.add(new Selection( 1, "�ʰ�", 0));
//
//		questions.add(new Question(quiz.getId(), "�̷Q�h����a?", null));
	}

	@Test
	public void searchQuizTest() {
		// null
		System.out.println("-----�S������-----");
		QuizSearchRes res = quizService.searchQuiz(null, null);
		List<Quiz> quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}

		// �u��title
		System.out.println("-----�u��title-----");
		res = quizService.searchQuiz("��", null);
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}

		// �u��state
		System.out.println("-----�u��state-----");
		res = quizService.searchQuiz(null, "�|���}�l");
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}

		// ��ӱ���
		System.out.println("-----��ӱ��󳣦�-----");
		res = quizService.searchQuiz("��", "�o����");
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}
	}

	@Test
	public void getQuizInfoTest() {
		// ���s�b��id
		quizService.getQuizInfo(0);
		// �s�b��id
		quizService.getQuizInfo(2);
	}

	@Test
	public void deleteSeleTest() {
		// ���s�bseleid
		System.out.println("-----���s�b��seleid-----");
		quizService.deleteSelection(0);
		// �s�bseleid
		System.out.println("-----�s�b��seleid-----");
		quizService.deleteSelection(11);
	}

	@Test
	public void deleteQuesTest() {
		// ���s�bqid
		System.out.println("-----���s�b��qid-----");
		quizService.deleteQuestion(0);
		// �s�bqid(���ﶵ)
		System.out.println("-----�s�b��qid(���ﹳ)-----");
		quizService.deleteQuestion(1);
		// �s�bqid
		System.out.println("-----�s�b��qid-----");
		quizService.deleteQuestion(2);
	}

	@Test
	public void deleteQuizTest() {
		// ���s�bquizid
		System.out.println("-----���s�b��quizid-----");
		quizService.deleteQuiz(0);
		// �s�bqid(��question & selection)
		System.out.println("-----�s�b��quizid-----");
		quizService.deleteQuiz(12);
	}

}
