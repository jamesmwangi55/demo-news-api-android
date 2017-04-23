package com.demonews.demo_news_api_android.data.articles.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("source_id")
    @Expose
    private String sourceId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("urlsToLogosSmall")
    @Expose
    private String urlsToLogosSmall;
    @SerializedName("urlsToLogosMedium")
    @Expose
    private String urlsToLogosMedium;
    @SerializedName("urlsToLogosLarge")
    @Expose
    private String urlsToLogosLarge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrlsToLogosSmall() {
        return urlsToLogosSmall;
    }

    public void setUrlsToLogosSmall(String urlsToLogosSmall) {
        this.urlsToLogosSmall = urlsToLogosSmall;
    }

    public String getUrlsToLogosMedium() {
        return urlsToLogosMedium;
    }

    public void setUrlsToLogosMedium(String urlsToLogosMedium) {
        this.urlsToLogosMedium = urlsToLogosMedium;
    }

    public String getUrlsToLogosLarge() {
        return urlsToLogosLarge;
    }

    public void setUrlsToLogosLarge(String urlsToLogosLarge) {
        this.urlsToLogosLarge = urlsToLogosLarge;
    }

}