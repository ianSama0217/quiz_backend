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
import com.example.quiz.service.ifs.QuizServer;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizSearchRes;

@SpringBootTest
public class QuizServiceTest {

	@Autowired
	private QuizServer quizServer;

	@Test
	public void createQuizTest() {
		Quiz quiz = new Quiz(1, "����", "�쩳�O�ťզX�٬O�ťթ�", "�w����", LocalDate.of(2023, 11, 17), LocalDate.of(2023, 11, 20));
		List<Question> questions = new ArrayList<Question>();
		questions.add(new Question(1, 1, "�U�_�t?", "radio"));
		questions.add(new Question(1, 2, "��ԣ", "textarea"));

		List<Selection> selections = new ArrayList<Selection>();
		selections.add(new Selection(1, 1, "��U�t", 0));
		selections.add(new Selection(1, 2, "�U��t", 0));
		selections.add(new Selection(1, 3, "�ťթ�", 0));

		quizServer.createQuiz(new QuizReq(quiz, questions, selections));

		quiz = new Quiz(3, "���N�׽լd", "��g�\�U�ݨ����N��", "�o����", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 31));
		questions = new ArrayList<Question>();
		questions.add(new Question(3, 6, "���ѥ��\���N��?", "radio"));
		questions.add(new Question(3, 7, "��h�N��", "textarea"));

		selections = new ArrayList<Selection>();
		selections.add(new Selection(6, 13, "�D�`���N", 25));
		selections.add(new Selection(6, 14, "���q", 0));
		selections.add(new Selection(6, 15, "�D�`�����N", 0));

		quizServer.createQuiz(new QuizReq(quiz, questions, selections));

		quiz = new Quiz(4, "�H��D������", "��X2023�̨��w�諸�D��", "�|���}�l", LocalDate.of(2023, 12, 25), LocalDate.of(2023, 12, 31));
		questions = new ArrayList<Question>();
		questions.add(new Question(4, 8, "�֬O�A�̳��w���D��", "checkbox"));
		questions.add(new Question(4, 9, "��ԣ", "textarea"));
		questions.add(new Question(4, 10, "�Q��L������", "textarea"));

		selections = new ArrayList<Selection>();
		selections.add(new Selection(8, 16, "�ǵh", 0));
		selections.add(new Selection(8, 17, "���ͤH", 0));
		selections.add(new Selection(8, 18, "��Ȥl", 0));
		selections.add(new Selection(8, 19, "�]��", 0));

		quizServer.createQuiz(new QuizReq(quiz, questions, selections));
	}

	@Test
	public void searchQuizTest() {
		// null
		System.out.println("-----�S������-----");
		QuizSearchRes res = quizServer.searchQuiz(null, null);
		List<Quiz> quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}

		// �u��title
		System.out.println("-----�u��title-----");
		res = quizServer.searchQuiz("��", null);
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}

		// �u��state
		System.out.println("-----�u��state-----");
		res = quizServer.searchQuiz(null, "�|���}�l");
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}

		// ��ӱ���
		System.out.println("-----��ӱ��󳣦�-----");
		res = quizServer.searchQuiz("��", "�o����");
		quizs = res.getQuizs();

		for (Quiz quiz : quizs) {
			System.out.println(quiz.getTitle());
		}
	}

	@Test
	public void deleteSeleTest() {
		// ���s�bseleid
		System.out.println("-----���s�b��seleid-----");
		quizServer.deleteSelection(0);
		// �s�bseleid
		System.out.println("-----�s�b��seleid-----");
		quizServer.deleteSelection(11);
	}

	@Test
	public void deleteQuesTest() {
		// ���s�bqid
		System.out.println("-----���s�b��qid-----");
		quizServer.deleteQuestion(0);
		// �s�bqid(���ﶵ)
		System.out.println("-----�s�b��qid(���ﹳ)-----");
		quizServer.deleteQuestion(1);
		// �s�bqid
		System.out.println("-----�s�b��qid-----");
		quizServer.deleteQuestion(2);
	}

	@Test
	public void deleteQuizTest() {
		// ���s�bquizid
		System.out.println("-----���s�b��quizid-----");
		quizServer.deleteQuiz(0);
		// �s�bqid(��question & selection)
		System.out.println("-----�s�b��quizid-----");
		quizServer.deleteQuiz(4);
	}

}
