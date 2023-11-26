package com.example.quiz.vo;

import java.util.List;

import com.example.quiz.entity.Userinfo;

public class CreateAnsReq {
	private List<Userinfo> userinfos;

	public CreateAnsReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateAnsReq(List<Userinfo> userinfos) {
		super();
		this.userinfos = userinfos;
	}

	public List<Userinfo> getUserinfos() {
		return userinfos;
	}

	public void setUserinfos(List<Userinfo> userinfos) {
		this.userinfos = userinfos;
	}
}
