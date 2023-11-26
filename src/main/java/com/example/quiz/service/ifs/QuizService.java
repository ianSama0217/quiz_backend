package com.example.quiz.service.ifs;

import com.example.quiz.entity.Userinfo;
import com.example.quiz.vo.QuizAnsRes;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;
import com.example.quiz.vo.QuizSearchRes;

public interface QuizService {
	public QuizRes createQuiz(QuizReq req);

	public QuizAnsRes createQuizAns(Userinfo userinfo);

	public QuizSearchRes searchQuiz(String title, String state);

	public QuizRes getQuizInfo(int id);

	public QuizAnsRes getQuizAns(int id);

	public QuizRes deleteQuiz(int id);
}
