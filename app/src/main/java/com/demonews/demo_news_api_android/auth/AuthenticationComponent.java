package com.demonews.demo_news_api_android.auth;

import com.demonews.demo_news_api_android.util.FragmentScoped;

import dagger.Component;

/**
 * Created by james on 4/22/2017.
 */
@FragmentScoped
@Component(modules = AuthenticationPresenterModule.class)
public interface AuthenticationComponent {
    void inject(AuthenticationActivity authenticationActivity);
}
