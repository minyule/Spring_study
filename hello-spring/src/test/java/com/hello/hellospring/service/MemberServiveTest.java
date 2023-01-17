package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiveTest {
//    MemberServive memberServive = new MemberServive();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

     // 자신이 직접 안넣고 다른 데에서 넣어줌 : dependency injection 의존성 주입
        MemberServive memberServive;
        MemoryMemberRepository memberRepository;

        @BeforeEach
        public void beforeEach () {
        memberRepository = new MemoryMemberRepository();
        memberServive = new MemberServive(memberRepository);

    }


        @AfterEach
        public void afterEach () {
        memberRepository.clearStore();
    }


        @Test
        void join () {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberServive.join(member);
        //then
        Member findMemeber = memberServive.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMemeber.getName());
    }

        @Test
        public void duplicatedException () {
        //given
        Member member1 = new Member();
        member1.setName("one1");

        Member member2 = new Member();
        member2.setName("one1");
        //when
        memberServive.join(member1);

        // join(members)를 했을 때, IllegalStateException.class 이게 발생해야 정상 동작
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberServive.join(member2));
        // 에러 메세지가 동일한지도 확인
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberServive.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //then
    }

        @Test
        void findMembers () {
    }

        @Test
        void findOne () {
    }
    }