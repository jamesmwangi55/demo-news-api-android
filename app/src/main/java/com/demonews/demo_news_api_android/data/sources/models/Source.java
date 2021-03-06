package com.demonews.demo_news_api_android.data.sources.models;

/**
 * Created by james on 4/22/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("source_id")
    @Expose
    private String sourceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

