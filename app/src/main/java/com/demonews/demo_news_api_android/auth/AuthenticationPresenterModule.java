package com.demonews.demo_news_api_android.auth;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by james on 4/22/2017.
 */
@Module
public class AuthenticationPresenterModule {
    private final AuthenticationContract.View mView;
    private final Context mContext;

    public AuthenticationPresenterModule(AuthenticationContract.View view, Context context){
        mContext = context;
        mView = view;
    }

    @Provides
    AuthenticationContract.View provideAuthenticationContractView(){
        return mView;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
