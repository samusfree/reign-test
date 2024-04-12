package com.reign.sgonzales.betest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDTO {
    private String objectId;
    private List<String> tags;
    private String author;
    private String text;
    private String title;
    private String url;
}
