package com.demonews.demo_news_api_android;

import android.app.Application;

import com.demonews.demo_news_api_android.data.sources.DaggerSourcesRepositoryComponent;
import com.demonews.demo_news_api_android.data.sources.SourcesRepositoryComponent;

/**
 * Created by james on 4/21/2017.
 */

public class DemoNewsApplication extends Application {

    private SourcesRepositoryComponent mSourcesRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mSourcesRepositoryComponent = DaggerSourcesRepositoryComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
    }

    public SourcesRepositoryComponent getSourcesRepositoryComponent(){
        return mSourcesRepositoryComponent;
    }
}
