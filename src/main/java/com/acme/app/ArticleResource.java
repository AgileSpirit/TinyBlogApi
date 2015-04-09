package com.acme.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleResource {

    @Autowired
    private ArticleRepository articleRepository;

    // curl -X GET http://{host}:{port}/api/articles -i -H "Content-Type: application/json"
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Article> getArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    // curl -X GET http://{host}:{port}/api/articles/{id} -i -H "Content-Type: application/json"
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Article getArticle(@PathVariable("id") Long articleId) {
        Article article = articleRepository.findOne(articleId);
        return article;
    }

    // curl -X POST http://{host}:{port}/api/articles -i -H "Accept: application/json" -H "Content-Type: application/json"
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Article createArticle(@RequestBody Article article) {
        if (article.getId() != null) {
            throw new PersistenceException("Article ID must be null");
        }
        return articleRepository.save(article);
    }

    // curl -X PUT http://{host}:{port}/api/articles -i -H "Accept: application/json" -H "Content-Type: application/json"
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Article updateArticle(@RequestBody Article article) {
        if (article.getId() == null) {
            throw new PersistenceException("Article ID must not be null");
        }
        if (!articleRepository.exists(article.getId())) {
            throw new PersistenceException("Article can not be updated because it does not exist or was not found");
        }
        return articleRepository.save(article);
    }

    // curl -X DELETE http://{host}:{port}/api/articles/{id} -i
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteArticle(@PathVariable("id") Long articleId) {
        if (!articleRepository.exists(articleId)) {
            throw new PersistenceException("Article can not be deleted because it does not exist or was not found");
        }
        articleRepository.delete(articleId);
    }


}
