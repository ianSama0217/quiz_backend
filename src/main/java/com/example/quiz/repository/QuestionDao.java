package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	/**
	 * 尋找question對應quizid的資料是否存在
	 **/
	public boolean existsByquizId(int quizId);

	/**
	 * 尋找question對應quizid的資料
	 **/
	public List<Question> findAllByquizId(int quizId);

	/**
	 * 將對應quizid的question資料也刪除
	 **/
	public void deleteAllByquizId(int quizId);

}
