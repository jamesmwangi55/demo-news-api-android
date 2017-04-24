package com.demonews.demo_news_api_android.data.articles;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demonews.demo_news_api_android.data.Remote;
import com.demonews.demo_news_api_android.data.articles.model.Article;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by james on 4/22/2017.
 */
@Singleton
public class ArticlesRepository implements ArticlesDataSource {

    private static final String TAG = ArticlesRepository.class.getSimpleName();

    private final ArticlesDataSource mArticlesRemoteDataSource;

    @Inject
    ArticlesRepository(@Remote ArticlesDataSource articlesRemoteDataSource){
        mArticlesRemoteDataSource = articlesRemoteDataSource;
    }

    @Override
    public void getArticles( String category, String source, @NonNull final LoadArticlesCallback callback) {
       mArticlesRemoteDataSource.getArticles(category, source, new LoadArticlesCallback() {
           @Override
           public void onArticlesLoaded(List<Article> articles) {
               callback.onArticlesLoaded(articles);
           }

           @Override
           public void onArticlesNotLoaded() {
                callback.onArticlesNotLoaded();
           }
       });
    }

    @Override
    public void getArticles(@NonNull final LoadArticlesCallback callback) {
        Log.i(TAG, "getArticles: ");
            mArticlesRemoteDataSource.getArticles(new LoadArticlesCallback() {
                @Override
                public void onArticlesLoaded(List<Article> articles) {
                    callback.onArticlesLoaded(articles);
                }

                @Override
                public void onArticlesNotLoaded() {
                    callback.onArticlesNotLoaded();
                }
            });
    }
}
