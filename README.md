# TinyBlogApi
Spring Boot Web application to provide REST API in JSON

Get the sources

```
$ git clone git@github.com:AgileSpirit/TinyBlogApi.git
```

Run the application

```
$ mvn spring-boot:run
```

Use the API
```
# GET /articles : retrieve all the articles
$ curl -X GET http://localhost:8080/api/articles -i -H "Accept: application/json" -H "Content-Type: application/json"

# GET /articles/{id} : retrieve one article by its ID
$ curl -X GET http://localhost:8080/api/articles/1 -i -H "Accept: application/json" -H "Content-Type: application/json"
```

