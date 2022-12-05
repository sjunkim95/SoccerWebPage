package com.example.spring03.dto;

import com.example.spring03.domain.Info;

import lombok.Data;

@Data
public class InfoCreateDto {

    private String title;
    private String content;
    private String author;
    
    public Info toEntity() {
        return Info.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}