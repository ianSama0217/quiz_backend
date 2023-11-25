package com.example.quiz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;

import com.example.quiz.repository.QuestionDao;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;
import com.example.quiz.vo.QuizSearchRes;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizDao quizDao;

	@Autowired
	private QuestionDao questionDao;

	@Override
	public QuizRes createQuiz(QuizReq req) {

		Quiz quiz = req.getQuiz();
		List<Question> questions = req.getQuestion();

		// �ŦX���� ->�x�s�ݨ�
		if (StringUtils.hasText(quiz.getTitle())) {
			quizDao.save(quiz);
			System.out.println("���\�s�Wquiz");
			int quizId = quiz.getId();

			// �p�G��question ->�O�s���D
			if (questions != null) {
				for (Question ques : questions) {
					ques.setQuizId(quizId); // �j�w�ݨ�id
					System.out.println("���\�s�W(" + ques.getqName() + ")��" + quiz.getTitle() + "����");
					questionDao.save(ques);

				}
			}
			return new QuizRes(RtnCode.SUCCESSFUL, quiz, questions);
		} else {
			return new QuizRes(RtnCode.QUIZ_ERROR, null);
		}
	}

	@Override
	public QuizSearchRes searchQuiz(String title, String state) {
		List<Quiz> quizs = new ArrayList<Quiz>();

		// �j�M��S����r & ���A�O�� ->��ܥ���
		if (!StringUtils.hasText(title) && !StringUtils.hasText(state)) {
			// ��ܥ����ݨ�
			quizs = quizDao.findAll();
		}

		// �u��title & ���A�O��
		if (StringUtils.hasText(title) && !StringUtils.hasText(state)) {
			quizs = quizDao.findByTitleContaining(title);
		}

		// �u�����A & title�O��
		if (!StringUtils.hasText(title) && StringUtils.hasText(state)) {
			quizs = quizDao.findByState(state);
		}

		// title�M���A����
		if (StringUtils.hasText(title) && StringUtils.hasText(state)) {
			quizs = quizDao.findByTitleContainingAndState(title, state);
		}

		return new QuizSearchRes(RtnCode.SUCCESSFUL, quizs);
	}

	@Override
	public QuizRes getQuizInfo(int id) {
		if (!quizDao.existsById(id)) {
			return new QuizRes(RtnCode.QUIZ_ID_NOT_FOUND);
		}

		Optional<Quiz> quiz = quizDao.findById(id);
		// ��X����quizId
		List<Question> questions = questionDao.findAllByquizId(id);

		// ��X���������D�ت�qId
		// ���o�Ҧ�qId
		List<Integer> qIds = new ArrayList<Integer>();
		for (Question ques : questions) {
			qIds.add(ques.getqId());
		}

		return new QuizRes(RtnCode.SUCCESSFUL, quiz.get(), questions);
	}

	@Transactional
	@Override
	public QuizRes deleteQuiz(int id) {
		// �p�GDB�s�b��ơA�R����ƪ�
		if (quizDao.existsById(id)) {
			quizDao.deleteById(id);
			System.out.println("���\�R���ݨ�");
			// �p�Gquiz���U�ٹ�����question�]�n�R��
			if (questionDao.existsByquizId(id)) {

				questionDao.deleteAllByquizId(id);
				System.out.println("���\�R���ݨ������D��");
			}
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		return new QuizRes(RtnCode.QUIZ_ID_NOT_FOUND);
	}

	@Transactional
	@Override
	public QuizRes deleteQuestion(int qId) {

		if (questionDao.existsById(qId)) {
			questionDao.deleteById(qId);

			return new QuizRes(RtnCode.SUCCESSFUL);
		}

		return new QuizRes(RtnCode.QUESTION_ID_NOT_FOUND);
	}

}
