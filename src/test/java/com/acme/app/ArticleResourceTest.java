package com.acme.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ArticleResourceTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleResourceTest.class);

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetArticles() throws Exception {
        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetArticle() throws Exception {
        String actual = mockMvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"title\":\"Lorem Ipsum\"")))
                .andReturn().getResponse().getContentAsString();

        LOGGER.info("GET '/api/articles/1' : " + actual);
    }

    @Test
    public void testCreateArticle() throws Exception {
        // Given
        ObjectMapper mapper = new ObjectMapper();

        Article article = new Article();
        article.setAuthor("M. Smith");
        article.setTitle("Test article");
        article.setPublicationDate("20/05/2015");
        article.setExcerpt("A simple article for testing");
        article.setContent("<p>Lorem ipsum dolor sit amet bla bla bla...</p>");

        // When
        MvcResult result = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(article)))
                .andReturn();
        Article actual = mapper.readValue(result.getResponse().getContentAsString(), Article.class);

        // Then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getAuthor()).isEqualTo(article.getAuthor());
        assertThat(actual.getTitle()).isEqualTo(article.getTitle());
        assertThat(actual.getPublicationDate()).isEqualTo(article.getPublicationDate());
        assertThat(actual.getExcerpt()).isEqualTo(article.getExcerpt());
        assertThat(actual.getContent()).isEqualTo(article.getContent());
    }

    @Test
    public void testUpdateArticle() throws Exception {

    }

    @Test
    public void testDeleteArticle() throws Exception {

    }
}