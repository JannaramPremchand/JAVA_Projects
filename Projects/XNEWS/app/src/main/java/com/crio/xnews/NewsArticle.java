package com.crio.xnews;
import com.fasterxml.jackson.annotation.JsonProperty;
// TODO: CRIO_TASK_MODULE_PROJECT
// Declare variables for the fields: "title","description","author","url","publishedAt" & "content." Ignore any other fields.
// Utilize your IDE to generate getters and setters for these variables.
// Use your IDE to generate the toString method to create a string representation of the variables.

public class NewsArticle {
    private String title;
    private String description;
    private String author;
    private String url;
    private String publishedAt;
    private String content;
    private String urlToImage; // Add this field

    // Nested class to handle the 'source' field
    public static class Source {
        private String id;
        private String name;

        // Getters and setters for Source fields
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private Source source;

    // Getters and setters for the NewsArticle fields
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    // Optionally, override toString() method
    @Override
    public String toString() {
        return "NewsArticle1{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", source=" + source +
                '}';
    }
}


