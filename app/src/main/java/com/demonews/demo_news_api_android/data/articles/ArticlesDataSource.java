package com.demonews.demo_news_api_android.data.articles;

import android.support.annotation.NonNull;

import com.demonews.demo_news_api_android.data.articles.model.Article;

import java.util.List;

/**
 * Created by james on 4/22/2017.
 */

public interface ArticlesDataSource {

    interface LoadArticlesCallback{
        void onArticlesLoaded(List<Article> articles);
        void onArticlesNotLoaded();
    }

    void getArticles(String category, String source, @NonNull LoadArticlesCallback callback);
    void getArticles(@NonNull LoadArticlesCallback callback);
}
