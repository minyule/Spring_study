package com.hello.hellospring.service;

import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean  // 멤버 서비스를 자동으로 빈으로 만듦. 생성자 필요
    public MemberServive memberServive() {
        return new MemberServive(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
