package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

	/**
	 * ·j´M¥þ³¡
	 **/
	@Query(value = "select * from quiz" + //
			" order by id desc", nativeQuery = true)
	public List<Quiz> findAll();

	/**
	 * ¼Ò½k·j´Mtitle
	 **/
	@Query(value = " select * from quiz" //
			+ " where lower(title)" //
			+ " like lower (CONCAT('%', :title, '%'))" //
			+ " order by id desc", //
			nativeQuery = true)
	public List<Quiz> findByTitleContaining(@Param("title") String Title);

	/**
	 * ·j´Mstate
	 **/
	@Query(value = " select * from quiz" //
			+ " where state = :state" //
			+ " order by id desc", //
			nativeQuery = true)
	public List<Quiz> findByState(@Param("state") String state);

	/**
	 * ·j´Mtitle + state
	 **/
	@Query(value = " select * from quiz" //
			+ " where lower(title)" //
			+ " like lower (CONCAT('%', :title, '%'))" //
			+ " and state = :state" //
			+ " order by id desc", //
			nativeQuery = true)
	public List<Quiz> findByTitleContainingAndState(//
			@Param("title") String title, //
			@Param("state") String state);
}
