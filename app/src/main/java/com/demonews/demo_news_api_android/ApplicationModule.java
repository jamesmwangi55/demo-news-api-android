package com.demonews.demo_news_api_android;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by james on 4/21/2017.
 */
@Module
public final class ApplicationModule {
    private final Context mContext;

    ApplicationModule(Context context){
        mContext = context;
    }

    @Provides
    Context contextProvider(){
        return mContext;
    }
}
