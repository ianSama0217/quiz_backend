package com.example.quiz.service.ifs;

import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;
import com.example.quiz.vo.QuizSearchRes;

public interface QuizService {
	public QuizRes createQuiz(QuizReq req);

	public QuizRes updateQuiz(QuizReq req);

	public QuizSearchRes searchQuiz(String title, String state);

	public QuizRes getQuizInfo(int id);
	
	public QuizRes deleteQuiz(int id);

	public QuizRes deleteQuestion(int qIds);

	public QuizRes deleteSelection(int seleId);

}
