var app = app || {};

app.articleService = (function () {

  'use strict';

  function getArticles(callback) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/api/articles",
      dataType: "json",
      success: function (data, httpStatus) {
        callback(data);
      },
      error: function (data, httpStatus, error) {
        callback(data, error);
      }
    });
  }

  function getArticle(id, callback) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/api/articles/" + id,
      dataType: "json",
      success: function (data, httpStatus) {
        callback(data);
      },
      error: function (data, httpStatus, error) {
        callback(data, error);
      }
    });
  }

  return {
    getArticles: getArticles,
    getArticle: getArticle
  };
})();