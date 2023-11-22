package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Selection;

@Repository
public interface SelectionDao extends JpaRepository<Selection, Integer> {
	
	/**
	 * 尋找sele對應qid的資料是否存在
	 **/
	public boolean existsByqId(int qId);
	
	/**
	 * 尋找sele對應qid的資料是否存在
	 **/
	public List<Selection> findAllByqId(int qId);
	
	/**
	 * 將對應qid的selection資料也刪除
	 **/
	public void deleteAllByqId(int qId);
	
	
}
