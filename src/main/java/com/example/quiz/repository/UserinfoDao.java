package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Userinfo;

@Repository
public interface UserinfoDao extends JpaRepository<Userinfo, Integer> {
	/**
	 * �M��userinfo����quizid����ƬO�_�s�b
	 **/
	public boolean existsByquizId(int quizId);

	/**
	 * �M��userinfo����quizid�����
	 **/
	public List<Userinfo> findAllByquizId(int quizId);

	/**
	 * �N����quizid��userinfo��Ƥ]�R��
	 **/
	public void deleteAllByquizId(int quizId);
}
