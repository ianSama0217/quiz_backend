package com.example.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.service.ifs.QuizServer;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;

@RestController
public class QuizController {

	@Autowired
	private QuizServer server;

	@PostMapping(value = "api/quiz/create")
	public QuizRes createQuiz(@RequestBody QuizReq req) {
		return server.createQuiz(req);
	}

}
