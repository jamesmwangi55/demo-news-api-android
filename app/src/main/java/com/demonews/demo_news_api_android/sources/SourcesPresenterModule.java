package com.demonews.demo_news_api_android.sources;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by james on 4/23/2017.
 */
@Module
public class SourcesPresenterModule {
    private final SourcesContract.View mView;
    private final Context mContext;

    public SourcesPresenterModule(SourcesContract.View view, Context context){
        mView = view;
        mContext = context;
    }

    @Provides
    SourcesContract.View provideSourcesContractView(){
        return mView;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
