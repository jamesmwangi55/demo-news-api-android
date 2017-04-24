package com.demonews.demo_news_api_android.data.articles.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demonews.demo_news_api_android.data.NewsApiEndpointInterface;
import com.demonews.demo_news_api_android.data.articles.ArticlesDataSource;
import com.demonews.demo_news_api_android.data.articles.model.Article;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by james on 4/22/2017.
 */

public class ArticlesRemoteDataSource implements ArticlesDataSource {

    private static final String TAG = ArticlesRemoteDataSource.class.getSimpleName();

    private final Retrofit mRetrofit;

    @Inject
    public ArticlesRemoteDataSource(Retrofit retrofit){
        mRetrofit = retrofit;
    }

    @Override
    public void getArticles(String category, String source, @NonNull final LoadArticlesCallback callback) {
        Log.i(TAG, "getArticles: ");
        final NewsApiEndpointInterface newsService = mRetrofit.create(NewsApiEndpointInterface.class);
        newsService.getArticles(category, source)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Article>>() {
                               @Override
                               public void call(List<Article> articles) {
                                   callback.onArticlesLoaded(articles);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable x) {
                                Log.e(TAG, "call: ", x);
                                callback.onArticlesNotLoaded();
                            }
                        });
    }

    @Override
    public void getArticles(@NonNull final LoadArticlesCallback callback) {
        Log.i(TAG, "getArticles: ");
        final NewsApiEndpointInterface newsService = mRetrofit.create(NewsApiEndpointInterface.class);
        newsService.getArticles(null, null)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Article>>() {
                               @Override
                               public void call(List<Article> articles) {
                                   callback.onArticlesLoaded(articles);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable x) {
                                Log.e(TAG, "call: ", x);
                                callback.onArticlesNotLoaded();
                            }
                        });

    }
}
