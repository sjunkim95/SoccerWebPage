package com.example.spring03.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring03.domain.Info;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class InfoRepositoryTest {
    
    @Autowired
    private InfoRepository infoRepository;
    
    @Test
    public void testSave() {
        
        Info entity = Info.builder()
                .title("공지")
                .content("두야")
                .author("admin")
                .clickCount(null)
                .build();
        
        log.info("save 전: {} | {} | {}", 
                entity, entity.getCreatedTime(), entity.getModifiedTime());
        
        infoRepository.save(entity); // insert 문장
        
        log.info("save 후: {} | {} | {}", 
                entity, entity.getCreatedTime(), entity.getModifiedTime());
    }
}