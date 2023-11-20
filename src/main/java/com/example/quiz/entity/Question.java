package com.example.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

	@Column(name = "quiz_id")
	private int quizId;

	@Id
	@Column(name = "q_id")
	private int qId;

	@Column(name = "q_name")
	private String qName;

	@Column(name = "selection_type")
	private String seleType;

	public Question() {
		super();
	}

	public Question(int quizId, int qId, String qName, String seleType) {
		super();
		this.quizId = quizId;
		this.qId = qId;
		this.qName = qName;
		this.seleType = seleType;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getqName() {
		return qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}

	public String getSeleType() {
		return seleType;
	}

	public void setSeleType(String seleType) {
		this.seleType = seleType;
	}

}
