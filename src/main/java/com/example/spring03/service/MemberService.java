package com.example.spring03.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring03.domain.Member;
import com.example.spring03.dto.MemberRegisterDto;
import com.example.spring03.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    
    public String checkUsername(String username) {
        log.info("checkUsername(username={})", username);
        
        Optional<Member> result = memberRepository.findByUsername(username);
        if (result.isPresent()) { 
            return "nok"; 
        } else {
            return "ok"; 
        }
        
    }

    public Member registerMember(MemberRegisterDto dto) {
        log.info("registerMember(dto={})", dto);
        
        
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Member entity = memberRepository.save(dto.toEntity());
        log.info("entity = {}", entity);
        
        return entity;
    }

}