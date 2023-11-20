package com.example.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "selection")
public class Selection {

	@Column(name = "q_id")
	@JsonProperty("q_id")
	private int qId;

	@Id
	@Column(name = "sele_id")
	@JsonProperty("sele_id")
	private int seleId;

	@Column(name = "content")
	private String content;

	@Column(name = "ans_count")
	@JsonProperty("ans_count")
	private int ansCount;

	public Selection() {
		super();
	}

	public Selection(int qId, int seleId, String content, int ansCount) {
		super();
		this.qId = qId;
		this.seleId = seleId;
		this.content = content;
		this.ansCount = ansCount;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public int getSeleId() {
		return seleId;
	}

	public void setSeleId(int seleId) {
		this.seleId = seleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAnsCount() {
		return ansCount;
	}

	public void setAnsCount(int ansCount) {
		this.ansCount = ansCount;
	}

}
