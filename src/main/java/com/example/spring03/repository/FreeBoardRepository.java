package com.example.spring03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.spring03.domain.BoardFree;


public interface FreeBoardRepository extends JpaRepository<BoardFree, Integer> {

    

}