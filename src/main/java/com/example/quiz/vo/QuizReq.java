package com.example.quiz.vo;

import java.util.List;

import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;

public class QuizReq {

	private Quiz quiz;

	private List<Question> question;

	public QuizReq() {
		super();
	}

	public QuizReq(Quiz quiz) {
		super();
		this.quiz = quiz;
	}

	public QuizReq(Quiz quiz, List<Question> question) {
		super();
		this.quiz = quiz;
		this.question = question;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}
}
