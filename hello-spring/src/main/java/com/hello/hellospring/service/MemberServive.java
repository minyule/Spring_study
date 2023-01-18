package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberServive {

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //테스트와 같은 애를 테스트하고 싶음.
    private final MemberRepository memberRepository;
    // 외부에서 넣어주도록 변경
    // 자신이 직접 안넣고 다른 데에서 넣어줌 : dependency injection 의존성 주입
    public MemberServive(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        // 같은 이름 있는 중복 회원 방지
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                    try {
//                        throw new IllegalAccessException("이미 존재하는 회원입니다.");
//                    } catch (IllegalAccessException e) {
//                        throw new RuntimeException(e);
//                    }
                });
    }

    //  전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
