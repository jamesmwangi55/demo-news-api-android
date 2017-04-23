package com.demonews.demo_news_api_android.data.articles;

import com.demonews.demo_news_api_android.data.Remote;
import com.demonews.demo_news_api_android.data.articles.remote.ArticlesRemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by james on 4/22/2017.
 */
@Module
abstract class ArticlesRepositoryModule {
    @Singleton
    @Binds
    @Remote
    abstract ArticlesDataSource provideArticlesRemoteDataSource(ArticlesRemoteDataSource articlesRemoteDataSource);
}
