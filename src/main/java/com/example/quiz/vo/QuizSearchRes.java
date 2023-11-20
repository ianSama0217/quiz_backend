package com.example.quiz.vo;

import java.util.List;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.entity.Quiz;

public class QuizSearchRes {
	private RtnCode rtnCode;

	private List<Quiz> quizs;

	public QuizSearchRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizSearchRes(RtnCode rtnCode, List<Quiz> quizs) {
		super();
		this.rtnCode = rtnCode;
		this.quizs = quizs;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public List<Quiz> getQuizs() {
		return quizs;
	}

	public void setQuizs(List<Quiz> quizs) {
		this.quizs = quizs;
	}

}
