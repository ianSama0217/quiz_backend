package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

	/**
	 * ¼Ò½k·j´Mtitle
	 **/
	public List<Quiz> findByTitleContaining(String Title);

	/**
	 * ·j´Mstate
	 **/
	public List<Quiz> findByState(String state);

	/**
	 * ·j´Mtitle + state
	 **/
	public List<Quiz> findByTitleContainingAndState(String title, String state);

}
