package com.demonews.demo_news_api_android.data.sources.remote;

import android.support.annotation.NonNull;

import com.demonews.demo_news_api_android.data.sources.SourcesDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by james on 4/22/2017.
 */
@Singleton
public class SourcesRemoteDataSource implements SourcesDataSource {
    @Inject
    public SourcesRemoteDataSource(){}

    @Override
    public void getSources(@NonNull LoadSourcesCallback callback) {

    }
}
