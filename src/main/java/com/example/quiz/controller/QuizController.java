package com.example.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.CreateAnsReq;
import com.example.quiz.vo.QuizAnsRes;
import com.example.quiz.vo.QuizReq;
import com.example.quiz.vo.QuizRes;
import com.example.quiz.vo.QuizSearchRes;

@CrossOrigin
@RestController
public class QuizController {

	@Autowired
	private QuizService service;

	@PostMapping(value = "quiz/create")
	public QuizRes createQuiz(@RequestBody QuizReq req) {
		return service.createQuiz(req);
	}

	@PostMapping(value = "quiz/create/answer")
	public QuizAnsRes createQuizAns(@RequestBody CreateAnsReq req) {
		return service.createQuizAns(req);
	}

	@GetMapping(value = "quiz/search")
	public QuizSearchRes searchQuiz(
			/* required設為false表示參數是可以不輸入的 */
			@RequestParam(required = false) String title, @RequestParam(required = false) String state) {
		return service.searchQuiz(title, state);
	}

	@GetMapping(value = "quiz/get")
	public QuizRes getQuizInfo(@RequestParam int id) {
		return service.getQuizInfo(id);
	}
	
	@GetMapping(value = "quiz/get/answer")
	public QuizAnsRes getQuizAns(@RequestParam int id) {
		return service.getQuizAns(id);
	}

	@PostMapping(value = "quiz/delete")
	public QuizRes deleteQuiz(@RequestParam int id) {
		return service.deleteQuiz(id);
	}
}
