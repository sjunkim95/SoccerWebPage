package com.example.spring03.dto;

import lombok.Data;

@Data
public class SoccerReplyResiterDto {
	
	// 필드 이름은 Ajax 요청에서 사용된 data 객체의 속성(property) 이름과 동일하게 작성.
	private Integer postId; // 댓글이 달린 포스트 아이디
	private String replyText; // 댓글 내용
	private String writer; // 댓글 작성자
	
}
