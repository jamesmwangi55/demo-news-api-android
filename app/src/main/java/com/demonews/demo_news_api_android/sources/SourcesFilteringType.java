package com.demonews.demo_news_api_android.sources;

/**
 * Created by james on 4/24/2017.
 */

public enum  SourcesFilteringType {

    ALL_SOURCES(""),
    BUSINESS("Business"),
    ENTERTAINMENT("Entertainment"),
    GAMING("Gaming"),
    GENERAL("General"),
    MUSIC("Music"),
    POLITICS("Politics"),
    SCIENCE_AND_NATURE("Science-and-Nature"),
    SPORT("sport"),
    TECHNOLOGY("Technology");


    private String mSource;

    SourcesFilteringType(String source){
        mSource = source;
    }

    public String source(){
        return mSource;
    }
}
