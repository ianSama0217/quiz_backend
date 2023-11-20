package com.example.quiz.service.ifs;

import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;

public interface QuizServer {
	public QuizRes createQuiz(QuizReq req);

	public QuizRes deleteQuiz();

}
