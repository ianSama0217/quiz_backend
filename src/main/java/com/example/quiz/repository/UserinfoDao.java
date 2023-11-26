package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Userinfo;

@Repository
public interface UserinfoDao extends JpaRepository<Userinfo, String> {
	/**
	 * �M��question����quizid����ƬO�_�s�b
	 **/
	public boolean existsByquizId(int quizId);

	/**
	 * �M��question����quizid�����
	 **/
	public List<Userinfo> findAllByquizId(int quizId);
}
