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

		// id<=0 或 title是空值->跳出
		if (quiz.getId() <= 0 || !StringUtils.hasText(quiz.getTitle())) {
			return new QuizRes(RtnCode.QUIZ_ERROR, null);
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
		return new QuizRes(RtnCode.SUCCESSFUL, quiz, questions, selections);
	}

	@Override
	public QuizRes updateQuiz(QuizReq req) {

		return null;
	}

	@Override
	public QuizSearchRes searchQuiz(String title, String state) {
		List<Quiz> quizs = new ArrayList<Quiz>();

		// 搜尋欄沒有文字 & 狀態是空 ->顯示全部
		if (!StringUtils.hasText(title) && !StringUtils.hasText(state)) {
			// 顯示全部問卷
			quizs = quizDao.findAll();
		}

		// 只有title & 狀態是空
		if (StringUtils.hasText(title) && !StringUtils.hasText(state)) {
			quizs = quizDao.findByTitleContaining(title);
		}

		// 只有狀態 & title是空
		if (!StringUtils.hasText(title) && StringUtils.hasText(state)) {
			quizs = quizDao.findByState(state);
		}

		// title和狀態都有
		if (StringUtils.hasText(title) && StringUtils.hasText(state)) {
			quizs = quizDao.findByTitleContainingAndState(title, state);
		}

		return new QuizSearchRes(RtnCode.SUCCESSFUL, quizs);
	}

	@Transactional
	@Override
	public QuizRes deleteQuiz(int id) {
		// 如果DB存在資料，刪除資料表
		if (quizDao.existsById(id)) {
			quizDao.deleteById(id);
			System.out.println("成功刪除問卷");
			// 如果quiz底下還對應的question也要刪除
			if (questionDao.existsByquizId(id)) {
				// 取得question的qId(用來刪除selection)
				List<Question> questions = questionDao.findAllByquizId(id);

				questionDao.deleteAllByquizId(id);
				System.out.println("成功刪除問卷內的題目");

				// 如果question底下還對應的selection也要刪除
				for (Question ques : questions) {
					// 刪除對應qid的selection資料
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
			// 如果question底下還對應的selection也要刪除
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
