package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Userinfo;

@Repository
public interface UserinfoDao extends JpaRepository<Userinfo, Integer> {
	/**
	 * 尋找userinfo對應quizid的資料是否存在
	 **/
	public boolean existsByquizId(int quizId);

	/**
	 * 尋找userinfo對應quizid的資料
	 **/
	public List<Userinfo> findAllByquizId(int quizId);

	/**
	 * 將對應quizid的userinfo資料也刪除
	 **/
	public void deleteAllByquizId(int quizId);
}
