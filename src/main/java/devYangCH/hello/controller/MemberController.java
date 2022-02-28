package devYangCH.hello.controller;

import devYangCH.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

// Setter Injection 방식을 통한 DI
// 아래 생성자를 통한 방법이 더 권장 된다.
// 조립 시점이 아니라도 접근할 수 있다는 것이 단점이다.
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
