package com.tencoding.blog_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog_1.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
