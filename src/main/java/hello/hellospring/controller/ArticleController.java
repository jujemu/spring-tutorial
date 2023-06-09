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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.MultiDoc;
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
        log.info("createArticle, " + articleForm.toString());
        // dto to entity
        Article article = articleForm.toEntity();

        // give entity repository to put on db
        Article saved = articleRepository.save(article);

        return "redirect:/article/" + saved.getId();
    }

    @GetMapping("/article/{id}")
    public String getArticleWithId(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
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
    public String showEditorArticle(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return "article/edit";
    }

    @PostMapping("/article/update")
    public String editArticle(ArticleForm form) {
        Article articleEntity = form.toEntity();
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null) {
            articleRepository.save(articleEntity);
        }
        return "redirect:/article/" + articleEntity.getId();
    }

    @GetMapping("/article/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다!");
        }
        return "redirect:/articles";
    }
}
