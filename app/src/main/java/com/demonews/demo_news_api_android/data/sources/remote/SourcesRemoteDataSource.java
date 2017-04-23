package com.demonews.demo_news_api_android.data.sources.remote;

import android.support.annotation.NonNull;

import com.demonews.demo_news_api_android.data.NewsApiEndpointInterface;
import com.demonews.demo_news_api_android.data.sources.SourcesDataSource;
import com.demonews.demo_news_api_android.data.sources.models.Source;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by james on 4/22/2017.
 */
@Singleton
public class SourcesRemoteDataSource implements SourcesDataSource {

    private static final String TAG = SourcesRemoteDataSource.class.getSimpleName();

    private final Retrofit mRetrofit;

    @Inject
    public SourcesRemoteDataSource(Retrofit retrofit){
        mRetrofit = retrofit;
    }

    @Override
    public void getSources(@NonNull final LoadSourcesCallback callback) {
        NewsApiEndpointInterface apiService = mRetrofit.create(NewsApiEndpointInterface.class);
        apiService.getSources()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Source>>() {
                    @Override
                    public void call(List<Source> sources) {
                        callback.onSourcesLoaded(sources);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onSourcesNotLoaded();
                    }
                });
    }
}
