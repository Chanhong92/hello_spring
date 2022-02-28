package devYangCH.hello.repository;

import devYangCH.hello.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //0,1,2 키 값을 생성해준다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return store.get(id);
        return Optional.ofNullable(store.get(id)); //Optional으로 감싸서 반환한다.
    }

    @Override
    public Optional<Member> findByName(String name) { //오잉?
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
