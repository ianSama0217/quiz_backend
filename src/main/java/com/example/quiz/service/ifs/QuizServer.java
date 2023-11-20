package com.example.quiz.service.ifs;

import java.time.LocalDate;

import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;

public interface QuizServer {
	public QuizRes createQuiz(QuizReq req);

	public QuizRes updateQuiz(QuizReq req);

	public QuizRes searchQuiz(String title, LocalDate startDate, LocalDate endDate);

	public QuizRes deleteQuiz();

}
