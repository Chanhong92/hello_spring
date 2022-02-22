package devYangCH.hello.repository;

import devYangCH.hello.domain.Member;

import java.util.List;
import java.util.Optional;

//구현 클래스를 변경할 수 있도록 인터페이스로 설계
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //java8에서 나옴. 혹시 Null일 때 Optional로 감싸서 반환한다.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
