package com.example.spring03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring03.domain.Info;

public interface InfoRepository extends JpaRepository<Info, Integer> {

	List<Info> findByOrderByIdDesc();
	
}