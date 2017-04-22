package com.demonews.demo_news_api_android.data.sources;

import com.demonews.demo_news_api_android.data.Remote;
import com.demonews.demo_news_api_android.data.sources.remote.SourcesRemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by james on 4/22/2017.
 */
@Module
abstract class SourcesRepositoryModule {
    @Singleton
    @Binds
    @Remote
    abstract SourcesDataSource provideSourcesRemoteDataSource(SourcesRemoteDataSource sourcesRemoteDataSource);
}
