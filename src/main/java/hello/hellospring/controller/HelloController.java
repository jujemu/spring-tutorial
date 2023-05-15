package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String helloController(Model model) {
        model.addAttribute("username", "주제무");
        return "hello";
    }

    @GetMapping("/test")
    public String testController() {
        return "test";
    }
}
