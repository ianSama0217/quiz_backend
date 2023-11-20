package com.example.quiz.vo;

import com.example.quiz.constants.RtnCode;

public class QuizRes {

	private RtnCode rtnCode;

	private QuizReq quizReq;

	public QuizRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizRes(RtnCode rtnCode, QuizReq quizReq) {
		super();
		this.rtnCode = rtnCode;
		this.quizReq = quizReq;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public QuizReq getQuizReq() {
		return quizReq;
	}

	public void setQuizReq(QuizReq quizReq) {
		this.quizReq = quizReq;
	}

}
