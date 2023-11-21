package com.example.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Selection;

@Repository
public interface SelectionDao extends JpaRepository<Selection, Integer> {
	
	/**
	 * �M��sele����qid����ƬO�_�s�b
	 **/
	public boolean existsByqId(int qId);
	
	/**
	 * �N����qid��selection��Ƥ]�R��
	 **/
	public void deleteAllByqId(int qId);
	
	
}
