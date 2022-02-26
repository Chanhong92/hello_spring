package devYangCH.hello.service;

import devYangCH.hello.domain.Member;
import devYangCH.hello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        // 이런식으로 수정하면 같은 memoryMemberRepository를 clearStore()하게 된다.
        // 이런 것을 DI (Dependency Injection)라고 한다.
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        //MemoryMemberRepository 클래스의 store는 static 멤버이니 이 clear가 동작한다.
        //그런데 곱게 보이지는 않네...
        //그러니 memberService 코드를 수정했다.
        memoryMemberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("spring");

        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); // 같은 이름으로 중복 회원가입

        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //방법2
        /*
        try {
            memberService.join(member2);
            fail(); //위에서 exception 이 안뜨면 실패다.
        } catch (IllegalStateException e)
        {
            //여기 들어오는 것이 정상이다.
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}