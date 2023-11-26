package com.example.quiz.vo;

import java.util.List;

import com.example.quiz.constants.RtnCode;
import com.example.quiz.entity.Userinfo;

public class QuizAnsRes {
	private RtnCode rtnCode;

	private Userinfo userinfo;

	private List<Userinfo> userinfos;

	public QuizAnsRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizAnsRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	/* save存單筆資料用的 */
	public QuizAnsRes(RtnCode rtnCode, Userinfo userinfo) {
		super();
		this.rtnCode = rtnCode;
		this.userinfo = userinfo;
	}

	/* get取得多筆資料用的 */
	public QuizAnsRes(RtnCode rtnCode, List<Userinfo> userinfos) {
		super();
		this.rtnCode = rtnCode;
		this.userinfos = userinfos;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public List<Userinfo> getUserinfos() {
		return userinfos;
	}

	public void setUserinfos(List<Userinfo> userinfos) {
		this.userinfos = userinfos;
	}

}
