package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	/**
	 * �M��question����quizid����ƬO�_�s�b
	 **/
	public boolean existsByquizId(int quizId);

	/**
	 * �M��question����quizid�����
	 **/
	public List<Question> findAllByquizId(int quizId);

	/**
	 * �N����quizid��question��Ƥ]�R��
	 **/
	public void deleteAllByquizId(int quizId);

}
