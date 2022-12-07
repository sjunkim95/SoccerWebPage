package com.example.spring03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// 컨트롤러 메서드들에서 @PreAuthorize, @PostAuthorize 애너테이션을 이용할 수 있도록.
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration // 스프링 부트 앱 환경 설정(configuration)을 자바 코드로 설정.
public class SecurityConfig {
    
    @Bean // 스프링 컨텍스트에서 생성, 관리하는 객체 - 필요한 곳에 의존성 주입.
    // 암호화(복호화) 알고리즘 객체 -> Spring Security에서는 비밀번호는 반드시 암호화를 해야 함.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*
    @Bean
    // 로그인/로그아웃 기능을 테스트하기 위한 가상의 사용자 정보 생성.
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user1 = User.builder()
                .username("user1") // 로그인 아이디
                .password(passwordEncoder().encode("1111")) // 로그인 아이디 - 반드시 암호화!
                .roles("USER") // ROLE_USER (일반 사용자) 권한
                .build();
        UserDetails user2 = User.builder()
                .username("user2")
                .password(passwordEncoder().encode("2222"))
                .roles("USER","ADMIN")
                .build();
        
        return new InMemoryUserDetailsManager(user1,user2);
    }
    */
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Spring Security는 GET 방식을 제외한 POST/PUT?DELETE 요청에서
        // CSRF 토큰을 요구함.
        // POST/PUT/DELETE 요청에서 CSRF 토큰을 서버로 전송하지 않으면 403 에러(forbidden)가 발생.
        // 기능 구현을 간단히 하기 위해서 Spring Security의 CSRF 기능을 비활성화.
        http.csrf().disable();
        
        // 로그인/로그아웃 관련 설정
        http.formLogin(Customizer.withDefaults()) // 스프링 시큐리티에서 제공하는 기본 로그인 폼 사용.
            .logout() // 로그앗웃 관련 설정 시작
            .logoutSuccessUrl("/login"); // 로그아웃 성공 후에 이동한 URL 설정.
        
        // 특정 경로(URL)에 시큐리티 적용:
        // 권한을 가지고 있는(로그인한) 사용자만 접근할 수 있는 경로
        // 익명 사용자(로그인하지 않은) 사용자도 접근할 수 있는 경로.
//        http.authorizeHttpRequests() // 요청에 따른 권한 설정 시작.
//            .antMatchers("/post/**", "/api/reply/**") // "/post", "/api/reply"로 시작하는 모든 경로
//            .hasRole("USER") // USER 권한을 가지고 있는 사용자만 접근 가능
//            .anyRequest() // 그 이외의 모든 요청
//            .permitAll(); // 로그인 여부와 상광 없이 허용.
        //-> 새로운 요청 경로/컨트롤러가 생길 때마다 설정 자바 코드를 변경을 해야 하는 번거로움.
        //-> 컨트롤러 메서드를 작성할 때 애너테이션을 사용해서 권한 설정을 할 수 있음.
        //-> (1) SecurityConfig 클래스에는 @EnableGlobalMethodSecurity 애너테이션을 사용.
        //-> (2) 각각의 컨트롤러 메서드에 @PreAuthorize 또는 @PostAuthorize 애너테이션을 사용.
        
        return http.build();
    }

}
