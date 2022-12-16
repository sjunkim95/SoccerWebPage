package com.example.spring03.dto;


import com.example.spring03.domain.SoccerPosts;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SoccerPostsUpdateDto {
	
	private Integer id;
	private String title;
	private String content;
	
	public  SoccerPosts toEntity() {
		return  SoccerPosts.builder()
				.id(id)
				.title(title)
				.content(content)
				.filename(null)
				.filenpath(null)
				.build();
	}
}
