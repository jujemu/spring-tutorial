package hello.hellospring.controller;

import hello.hellospring.entity.Article;
import hello.hellospring.dto.ArticleForm;
import hello.hellospring.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/article/new")
    public String showArticleForm() {
        return "article/new";
    }

    @PostMapping("/article/create")
    public String createArticle(ArticleForm articleForm) {
        // dto to entity
        Article article = articleForm.toEntity();

        // give entity repository to put on db
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/article/" + saved.getId();
    }

    @GetMapping("/article/{id}")
    public String getArticleWithId(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        log.info(articleEntity.toString());
        model.addAttribute("article", articleEntity);
        return "article/show";
    }

    @GetMapping("/articles")
    public String getAllArticles(Model model) {
        List<Article> articlesEntityList = articleRepository.findAll();
        model.addAttribute("articles", articlesEntityList);

        return "article/index";
    }

    @GetMapping("/article/{id}/edit")
    public String editArticle(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return "article/edit";
    }
}
