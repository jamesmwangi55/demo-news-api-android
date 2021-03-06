package com.demonews.demo_news_api_android.articles;

import com.demonews.demo_news_api_android.data.articles.ArticlesRepositoryComponent;
import com.demonews.demo_news_api_android.util.FragmentScoped;

import dagger.Component;

/**
 * Created by james on 4/22/2017.
 */
@FragmentScoped
@Component(dependencies = {ArticlesRepositoryComponent.class},
        modules = ArticlesPresenterModule.class)
public interface ArticlesComponent {
    void inject(ArticlesFragment articlesFragment);
    void inject(FilteredArticlesActivity filteredArticlesActivity);
}
