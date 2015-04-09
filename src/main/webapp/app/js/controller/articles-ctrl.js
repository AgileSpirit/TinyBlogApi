var app = app || {};

app.articlesCtrl = function() {
    
    'use strict';
    
    // Dependencies
    var service;
    
    // Model
    var articles = undefined;

    // Components
    var $articles,
        $articleTemplate;

    function declareComponents() {
      $articles = $('.article-list');
      $articleTemplate = $('li:first', $articles);
    }

    function loadModel() {
        articles = service.getArticles(function(data, error) {
          if (error) {
            alert(error);
            return false;
          }
          articles = data;
          renderView();
        });
    }
    
    function renderView() {
      if (articles) {
        for (var i = 0; i < articles.length; i++) {
          // on récupère l'article à afficher
          var article = articles[i];

          // on récupère les différents éléments graphiques qu'on va vouloir renseigner
          var $article = $articleTemplate.clone(true);
          var $articleLink = $('.article-link', $article);
          var $articleAuthor = $('.article-author', $article);
          var $articlePublicationDate = $('.article-publication-date', $article);
          var $articleExcerpt = $('.article-excerpt', $article);

          // on configure les valeurs des éléments graphiques (titre, auteur, etc.)
          $articleLink.text("#" + article.id + " - " + article.title);
          $articleLink.attr("href", "article.html?id=" + article.id);
          $articleAuthor.text(article.author);
          $articlePublicationDate.text(article.publicationDate);
          $articleExcerpt.html(article.excerpt);

          // on rajoute l'article dans la liste des articles
          $articles.append($article);
        }
        // on cache l'item template qui a fini de jouer son rôle
        $articleTemplate.hide();
      }
    }
    
    function init() {
        service = app.articleService;
        declareComponents();
        loadModel();
        renderView();
    }
    
    return {
        init: init
    };
}();