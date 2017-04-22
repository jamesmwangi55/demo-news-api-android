package com.demonews.demo_news_api_android.data.sources;

import android.support.annotation.NonNull;

import com.demonews.demo_news_api_android.data.Remote;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by james on 4/22/2017.
 */
@Singleton
public class SourcesRepository implements SourcesDataSource{

    private final SourcesDataSource mSourcesRemoteDataSource;

    @Inject
    SourcesRepository(@Remote SourcesDataSource sourcesRemoteDataSource){
        mSourcesRemoteDataSource = sourcesRemoteDataSource;
    }

    @Override
    public void getSources(@NonNull LoadSourcesCallback callback) {

    }
}
