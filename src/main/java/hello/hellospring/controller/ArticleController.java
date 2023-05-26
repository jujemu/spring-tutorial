package hello.hellospring.controller;

import hello.hellospring.entity.Article;
import hello.hellospring.dto.ArticleForm;
import hello.hellospring.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/article/new")
    public String showArticleForm(){
        return "article_form";
    }
    @PostMapping("/article/create")
    public String createArticle(ArticleForm articleForm) {
        // dto to entity
        Article article = articleForm.toEntity();

        // give entity repository to put on db
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "";
    }
}
