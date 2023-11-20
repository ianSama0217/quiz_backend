package com.example.quiz.service.ifs;

import java.util.List;

import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;
import com.example.quiz.vo.QuizSearchRes;

public interface QuizServer {
	public QuizRes createQuiz(QuizReq req);

	public QuizRes updateQuiz(QuizReq req);

	public QuizSearchRes searchQuiz(String title);

	public QuizRes deleteQuiz(QuizReq req);

	public QuizRes deleteQuestion(List<Integer> qIds);
	
	public QuizRes deleteSelection(int seleId);

}
