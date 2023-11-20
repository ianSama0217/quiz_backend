package com.example.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Userinfo;

@Repository
public interface UserinfoDao extends JpaRepository<Userinfo, String> {

}
