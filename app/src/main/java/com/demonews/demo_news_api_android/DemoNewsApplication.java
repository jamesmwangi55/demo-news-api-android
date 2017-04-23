package com.demonews.demo_news_api_android;

import android.app.Application;

import com.demonews.demo_news_api_android.data.articles.ArticlesRepositoryComponent;
import com.demonews.demo_news_api_android.data.articles.DaggerArticlesRepositoryComponent;
import com.demonews.demo_news_api_android.data.sources.DaggerSourcesRepositoryComponent;
import com.demonews.demo_news_api_android.data.sources.SourcesRepositoryComponent;

/**
 * Created by james on 4/21/2017.
 */

public class DemoNewsApplication extends Application {

    private SourcesRepositoryComponent mSourcesRepositoryComponent;
    private ArticlesRepositoryComponent mArticlesRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mSourcesRepositoryComponent = DaggerSourcesRepositoryComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();

        mArticlesRepositoryComponent = DaggerArticlesRepositoryComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();

    }

    public SourcesRepositoryComponent getSourcesRepositoryComponent(){
        return mSourcesRepositoryComponent;
    }

    public ArticlesRepositoryComponent getArticlesRepositoryComponent(){
        return mArticlesRepositoryComponent;
    }

}
