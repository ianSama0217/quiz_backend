package com.example.quiz.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;
import com.example.quiz.entity.Selection;
import com.example.quiz.repository.QuestionDao;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.repository.SelectionDao;
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

	@Autowired
	private SelectionDao seleDao;

	@Override
	public QuizRes createQuiz(QuizReq req) {

		Quiz quiz = req.getQuiz();
		List<Question> questions = req.getQuestion();
		List<Selection> selections = req.getSelection();

		// id<=0 �� title�O�ŭ�->���X
		if (quiz.getId() <= 0 || !StringUtils.hasText(quiz.getTitle())) {
			return new QuizRes(RtnCode.QUIZ_ERROR, null);
		}

		quizDao.save(quiz);

		for (Question ques : questions) {
			// id�ŦXquestion��id->�s�J
			if (quiz.getId() == ques.getQuizId()) {
				questionDao.save(ques);

				for (Selection sele : selections) {
					// id�ŦXselection��id->�s�J
					if (ques.getqId() == sele.getqId()) {
						seleDao.save(sele);
					}
				}
			}
		}
		return new QuizRes(RtnCode.SUCCESSFUL, quiz, questions, selections);
	}

	@Override
	public QuizRes updateQuiz(QuizReq req) {

		return null;
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

	@Transactional
	@Override
	public QuizRes deleteQuiz(int id) {
		// �p�GDB�s�b��ơA�R����ƪ�
		if (quizDao.existsById(id)) {
			quizDao.deleteById(id);
			System.out.println("���\�R���ݨ�");
			// �p�Gquiz���U�ٹ�����question�]�n�R��
			if (questionDao.existsByquizId(id)) {
				// ���oquestion��qId(�ΨӧR��selection)
				List<Question> questions = questionDao.findAllByquizId(id);

				questionDao.deleteAllByquizId(id);
				System.out.println("���\�R���ݨ������D��");

				// �p�Gquestion���U�ٹ�����selection�]�n�R��
				for (Question ques : questions) {
					// �R������qid��selection���
					if (seleDao.existsByqId(ques.getqId())) {
						seleDao.deleteAllByqId(ques.getqId());
					}
				}
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
			// �p�Gquestion���U�ٹ�����selection�]�n�R��
			if (seleDao.existsByqId(qId)) {
				seleDao.deleteAllByqId(qId);
			}
			return new QuizRes(RtnCode.SUCCESSFUL);
		}

		return new QuizRes(RtnCode.QUESTION_ID_NOT_FOUND);
	}

	@Override
	public QuizRes deleteSelection(int seleId) {
		if (seleDao.existsById(seleId)) {
			seleDao.deleteById(seleId);
			return new QuizRes(RtnCode.SUCCESSFUL);
		}

		return new QuizRes(RtnCode.SELECTION_ID_NOT_FOUND);
	}

}
