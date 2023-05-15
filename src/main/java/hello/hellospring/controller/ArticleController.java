package hello.hellospring.controller;

import hello.hellospring.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/article/new")
    public String showArticleForm(){
        return "articleform";
    }
    @PostMapping("/article/create")
    public String createArticle(ArticleForm articleForm) {
        System.out.println(articleForm.toString());
        return "";
    }
}
