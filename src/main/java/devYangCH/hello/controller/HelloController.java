package devYangCH.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //리턴되는 값은 View 를 통해서 출력되지 않고 HTTP Response Body 에 직접 전달
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //처음으로 String 이 아닌 객체를 넘겼음
    }

    static class Hello { //자바빈(JavaBean) 규약이란?
        private String name;

        public String getName() { //프로퍼티 접근 방식
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
