package com.demonews.demo_news_api_android.articles;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by james on 4/22/2017.
 */
@Module
public class ArticlesPresenterModule {
    private final ArticlesContract.View mView;
    private final Context mContext;

    public ArticlesPresenterModule(ArticlesContract.View view, Context context){
        mView = view;
        mContext = context;
    }

    @Provides
    ArticlesContract.View provideArticlesContractView(){
        return mView;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }


}
