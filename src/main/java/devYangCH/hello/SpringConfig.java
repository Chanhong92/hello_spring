package devYangCH.hello;

import devYangCH.hello.repository.MemberRepository;
import devYangCH.hello.repository.MemoryMemberRepository;
import devYangCH.hello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
