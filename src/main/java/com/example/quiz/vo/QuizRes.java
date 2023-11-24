package com.example.quiz.vo;

import java.util.List;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;

public class QuizRes {

	private RtnCode rtnCode;

	private Quiz quiz;

	private List<Question> question;

	public QuizRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public QuizRes(RtnCode rtnCode, Quiz quiz) {
		super();
		this.rtnCode = rtnCode;
		this.quiz = quiz;
	}

	public QuizRes(RtnCode rtnCode, Quiz quiz, List<Question> question) {
		super();
		this.rtnCode = rtnCode;
		this.quiz = quiz;
		this.question = question;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
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
