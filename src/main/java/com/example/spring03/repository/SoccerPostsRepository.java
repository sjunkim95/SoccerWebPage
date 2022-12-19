package com.example.spring03.repository;




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring03.domain.SoccerPosts;

public interface SoccerPostsRepository extends JpaRepository<SoccerPosts, Integer> {

	// select * from POSTS order by ID desc
	Page<SoccerPosts> findByOrderByIdDesc(Pageable pageable);
	
	Page<SoccerPosts> findByTitleContaining(String title, Pageable pageable);
	Page<SoccerPosts> findByContentContaining(String content, Pageable pageable);
	Page<SoccerPosts> findByAuthorContaining(String author, Pageable pageable);
	Page<SoccerPosts> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
	
	Page<SoccerPosts> findByCategoryIgnoreCaseContainingOrderByIdDesc(String category, Pageable pageable);
	
	// 조회수 증가
	@Modifying
	@Query
	("update SOCCER_POSTS s "
			+ "set s.clickCount = s.clickCount+1 "
			+ "where s.id = :id ")
	void clickCount(@Param(value = "id") Integer clickCount);
	
	//	JPQL(Java Persistence Query Language)
	// 검색
    @Query(
        "select s from SOCCER_POSTS s "
            + " where lower(s.title) like lower('%' || :keyword || '%') "
            + " or lower(s.content) like lower('%' || :keyword || '%') "
            + " order by s.id desc"
    )
    Page<SoccerPosts> searchByKeyword(@Param(value = "keyword") String keyword, Pageable pageable);
	

}
