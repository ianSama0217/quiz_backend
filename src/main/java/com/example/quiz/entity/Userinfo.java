package com.example.quiz.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "userinfo")
public class Userinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ans_id")
	private int ansId;

	@Column(name = "quiz_id")
	@JsonProperty("quiz_id")
	private int quizId;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_number")
	@JsonProperty("phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "ans")
	private String ans;

	@Column(name = "date_time")
	@JsonProperty("date_time")
	private LocalDateTime dateTime;

	public Userinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Userinfo(int quizId, String name, String phoneNumber, String email, String ans, LocalDateTime dateTime) {
		super();
		this.quizId = quizId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.ans = ans;
		this.dateTime = dateTime;
	}

	/* test使用的建構方法 */
	public Userinfo(int quizId, String name, String phoneNumber, String email, String ans) {
		super();
		this.quizId = quizId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.ans = ans;
	}

	public int getAnsId() {
		return ansId;
	}

	public void setAnsId(int ansId) {
		this.ansId = ansId;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}
