package com.example.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Selection;

@Repository
public interface SelectionDao extends JpaRepository<Selection, Integer> {

	/**
	 * �R��question�A�N����id����Ƥ]�R��
	 **/
	public void deleteByqId(int qId);
}
