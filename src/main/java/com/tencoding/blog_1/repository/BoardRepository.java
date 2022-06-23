package com.tencoding.blog_1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tencoding.blog_1.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	// 네이티브 쿼리 - 쿼리 직접 만들 수 있음, (서브쿼리를 사용할 때), 기존의 칼럼만 사용가능하기 때문에 다른 가공하는 것이 필요한 경우
	@Modifying // 네이티브 쿼리를 만드는 경우, 수정/삭제/저장 할 때 붙여야 함.
	@Query(value = "DELETE FROM board WHERE id = :id", nativeQuery = true)
	int mDeleteById(int id);
	
	// publiv abstact 생략
	@Query(value = "SELECT * FROM board WHERE id = :id", nativeQuery = true)
    Optional<Board> mFindById(int id);
	
}
