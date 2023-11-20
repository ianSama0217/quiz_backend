package com.example.quiz.service.impl;

import java.util.List;

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
import com.example.quiz.service.ifs.QuizServer;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;

@Service
public class QuizServiceImpl implements QuizServer {

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

		// id<=0 和 title是空值->跳出
		if (quiz.getId() > 0 && StringUtils.isEmpty(quiz.getTitle())) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ERROR, null);
		}

		quizDao.save(quiz);

		for (Question ques : questions) {
			// id符合question的id->存入
			if (quiz.getId() == ques.getQuizId()) {
				questionDao.save(ques);

				for (Selection sele : selections) {
					// id符合selection的id->存入
					if (ques.getqId() == sele.getqId()) {
						seleDao.save(sele);
					}
				}
			}
		}
		return new QuizRes(RtnCode.SUCCESSFUL, req);

	}

	@Override
	public QuizRes deleteQuiz() {
		quizDao.deleteAll();
		questionDao.deleteAll();
		seleDao.deleteAll();

		return new QuizRes(RtnCode.SUCCESSFUL, null);
	}

}
