package com.example.spring03.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring03.domain.Info;
import com.example.spring03.dto.InfoCreateDto;
import com.example.spring03.repository.InfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 필드를 초기화하는 생성자.
@Service // 스프링 컨텍스트에 Service 컴포넌트로 등록.
public class InfoService {
    
    private final InfoRepository infoRepository;

    public List<Info>read() {
        log.info("read()");
        
        List<Info> entity = infoRepository.findByOrderByIdDesc();
        return entity;
    }

    public Info create(InfoCreateDto dto) {
          log.info("create(dto={})", dto);
            
            Info entity = infoRepository.save(dto.toEntity());
            
            return entity;
    }

    public Info read(Integer id) {
        log.info("read(id={})", id);
        return infoRepository.findById(id).get();
    }
    
    
}