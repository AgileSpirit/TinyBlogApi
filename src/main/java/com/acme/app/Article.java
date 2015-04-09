package com.acme.app;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private String publicationDate;

    @Lob
    @Column(columnDefinition = "VARCHAR", length = 65535)
    private String excerpt;

    @Lob
    @Column(columnDefinition = "VARCHAR", length = 65535)
    private String content;

}
