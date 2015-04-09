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




1. Package to WAR file

http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-a-deployable-war-file

```
<packaging>war</packaging>
...
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

```
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
```

2. Declare application properties

http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#appendix

3. Add Lombok

http://jnb.ociweb.com/jnb/jnbJan2010.html#installation

```
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>0.9.2</version>
    </dependency>
</dependencies>
<repositories>
    <repository>
        <id>projectlombok.org</id>
        <url>http://projectlombok.org/mavenrepo</url>
    </repository>
</repositories>
```

4. Declare Domain Model class Article.java

5. Declare Controller class ArticleController.java

6. Declare Repository class ArticleRepository.java