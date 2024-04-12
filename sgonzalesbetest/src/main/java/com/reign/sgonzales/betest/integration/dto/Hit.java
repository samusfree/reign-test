package com.reign.sgonzales.betest.integration.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Date;
import java.util.List;

public record Hit(@JsonAlias("objectID") String objectId, @JsonAlias("_tags") List<String> tags, String author,
                  @JsonAlias({ "comment_text", "story_text" }) String text, @JsonAlias("created_at") Date createAt,
                  @JsonAlias("created_at_i") int createdAtTimestamp,
                  @JsonAlias({ "story_title", "title" }) String title, @JsonAlias({ "story_url", "url" }) String url,
                  @JsonAlias("updated_at") Date updatedAt) {
}