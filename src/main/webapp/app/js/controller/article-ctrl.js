var app = app || {};

app.articleCtrl = function() {
    
    'use strict';
    
    // Dependencies
    var service;
    
    // Model
    var article = undefined;

    // Components
    var $article,
        $articleLink,
        $articleAuthor,
        $articlePublicationDate,
        $articleContent;

    function getParameterByName(name) {
      name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
      var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
      return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }

    function declareComponents() {
      $article = $(".article");
      $articleLink = $('.article-link', $article);
      $articleAuthor = $('.article-author', $article);
      $articlePublicationDate = $('.article-publication-date', $article);
      $articleContent = $('.article-content', $article);
    }

    function loadModel() {
      var id = getParameterByName("id");
      article = service.getArticle(id, function(data, error) {
        if (error) {
          alert(error);
          return false;
        }
        article = data;
        renderView();
      });
    }

    function renderView() {
      if (article) {
        $articleLink.text("#" + article.id + " - " + article.title);
        $articleLink.attr("href", "article.html?id=" + article.id);
        $articleAuthor.text(article.author);
        $articlePublicationDate.text(article.publicationDate);
        $articleContent.html(article.content);
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