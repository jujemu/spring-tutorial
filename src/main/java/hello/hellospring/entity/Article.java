package hello.hellospring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column private String title;
    @Column private String content;

    public void patch(Article article) {
        if (article.getTitle() != null) {
            this.title = article.getTitle();
        }
        if (article.getContent() != null) {
            this.content = article.getContent();
        }
    }
}
