package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Userinfo;

@Repository
public interface UserinfoDao extends JpaRepository<Userinfo, String> {
	/**
	 * 尋找question對應quizid的資料是否存在
	 **/
	public boolean existsByquizId(int quizId);

	/**
	 * 尋找question對應quizid的資料
	 **/
	public List<Userinfo> findAllByquizId(int quizId);
}
