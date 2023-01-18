package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0, 1, 2... 키값을 생성해주는 기능
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // autoincrease 기능
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Optional.ofNullable: null 값이어도 반환이 가능하게 만들어줌. client에서 처리할 수 있도록
    }

    @Override
    public Optional<Member> findByName(String name) { // 맵을 돌면서 이름과 같은 값이 찾아지면 반환. 없으면 Optional로 감쌌기 때문에 null 반환 가능
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 스토어에 있는 멤버들이 반환됨.
    }

    public void clearStore() {
        store.clear();
    }
}
