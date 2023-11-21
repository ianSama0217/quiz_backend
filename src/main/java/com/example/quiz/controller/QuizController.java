package com.example.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.service.ifs.QuizServer;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;
import com.example.quiz.vo.QuizSearchReq;
import com.example.quiz.vo.QuizSearchRes;

@CrossOrigin
@RestController
public class QuizController {

	@Autowired
	private QuizServer server;

	@PostMapping(value = "api/quiz/create")
	public QuizRes createQuiz(@RequestBody QuizReq req) {
		return server.createQuiz(req);
	}

//	@GetMapping(value = "api/quiz/search")
//	public QuizSearchRes searchQuiz(@RequestParam(required = false) QuizSearchReq req) {
//		return server.searchQuiz(req.getTitle(), req.getState());
//	}

	@GetMapping(value = "api/quiz/search")
	public QuizSearchRes searchQuiz(
			/* required設為false表示參數是可以不輸入的 */
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String state) {
		return server.searchQuiz(title, state);
	}

}
