package com.example.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Selection;

@Repository
public interface SelectionDao extends JpaRepository<Selection, Integer> {

	/**
	 * 刪除question，將對應id的資料也刪除
	 **/
	public void deleteByqId(int qId);
}
