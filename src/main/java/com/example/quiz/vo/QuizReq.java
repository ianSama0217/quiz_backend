package com.example.quiz.vo;

import java.util.List;

import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;
import com.example.quiz.entity.Selection;

public class QuizReq {

	private Quiz quiz;

	private List<Question> question;

	private List<Selection> selection;

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

	public QuizReq(Quiz quiz, List<Question> question, List<Selection> selection) {
		super();
		this.quiz = quiz;
		this.question = question;
		this.selection = selection;
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

	public List<Selection> getSelection() {
		return selection;
	}

	public void setSelection(List<Selection> selection) {
		this.selection = selection;
	}

}
