package com.demonews.demo_news_api_android.data.articles;

import com.demonews.demo_news_api_android.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by james on 4/22/2017.
 */
@Singleton
@Component(modules = {ArticlesRepositoryModule.class, ApplicationModule.class})
public interface ArticlesRepositoryComponent {
    ArticlesRepository articlesRepository();
}
