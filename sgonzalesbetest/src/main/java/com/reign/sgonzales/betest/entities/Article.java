package com.reign.sgonzales.betest.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("articles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    private String objectID;
    private List<String> tags;
    private String author;
    private String text;
    private Date createAt;
    private int createdAtTimestamp;
    private String title;
    private String url;
    private Date updatedAt;
}
