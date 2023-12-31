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
import com.example.quiz.entity.Userinfo;
import com.example.quiz.repository.QuestionDao;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.repository.UserinfoDao;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.CreateAnsReq;
import com.example.quiz.vo.QuizAnsRes;
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
	private UserinfoDao userinfoDao;

	@Override
	public QuizRes createQuiz(QuizReq req) {

		Quiz quiz = req.getQuiz();
		List<Question> questions = req.getQuestion();

		// 符合條件 ->儲存問卷
		if (StringUtils.hasText(quiz.getTitle())) {
			quizDao.save(quiz);
			System.out.println("成功新增quiz");
			int quizId = quiz.getId();

			// 如果有question ->保存問題
			if (questions != null) {
				for (Question ques : questions) {
					ques.setQuizId(quizId); // 綁定問卷id
					System.out.println("成功新增(" + ques.getqName() + ")到" + quiz.getTitle() + "之中");
					questionDao.save(ques);

				}
			}
			return new QuizRes(RtnCode.SUCCESSFUL, quiz, questions);
		} else {
			return new QuizRes(RtnCode.QUIZ_ERROR, null);
		}
	}

	@Override
	public QuizAnsRes createQuizAns(CreateAnsReq req) {
		/* id對應問卷 && 必填資料有填寫 -> 儲存資料 */
		for (Userinfo user : req.getUserinfos()) {
			if (StringUtils.hasText(user.getEmail()) && //
					StringUtils.hasText(user.getAns()) && //
					StringUtils.hasText(user.getName())) {
				userinfoDao.saveAll(req.getUserinfos());
				return new QuizAnsRes(RtnCode.SUCCESSFUL, req.getUserinfos());
			}
		}
		return new QuizAnsRes(RtnCode.QUIZ_ERROR, null);
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

	@Override
	public QuizRes getQuizInfo(int id) {
		if (!quizDao.existsById(id)) {
			return new QuizRes(RtnCode.QUIZ_ID_NOT_FOUND);
		}

		Optional<Quiz> quiz = quizDao.findById(id);
		// 找出對應quizId
		List<Question> questions = questionDao.findAllByquizId(id);

		return new QuizRes(RtnCode.SUCCESSFUL, quiz.get(), questions);
	}

	@Override
	public QuizAnsRes getUserAns(int id) {
		if (!userinfoDao.existsById(id)) {
			return new QuizAnsRes(RtnCode.QUIZ_ERROR);
		}
		Optional<Userinfo> userinfo = userinfoDao.findById(id);
		return new QuizAnsRes(userinfo.get(), RtnCode.SUCCESSFUL);
	}

	@Override
	public QuizAnsRes getQuizAns(int id) {
		if (!quizDao.existsById(id)) {
			return new QuizAnsRes(RtnCode.QUIZ_ID_NOT_FOUND);
		}

		// 找出對應quizId
		List<Userinfo> userinfos = userinfoDao.findAllByquizId(id);

		return new QuizAnsRes(RtnCode.SUCCESSFUL, userinfos);
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

				questionDao.deleteAllByquizId(id);
				System.out.println("成功刪除問卷內的題目");
			}

			// 如果quiz底下還對應的userinfo也要刪除
			if (userinfoDao.existsByquizId(id)) {

				userinfoDao.deleteAllByquizId(id);
				System.out.println("成功刪除問卷作答紀錄");
			}
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		return new QuizRes(RtnCode.QUIZ_ID_NOT_FOUND);
	}

}
