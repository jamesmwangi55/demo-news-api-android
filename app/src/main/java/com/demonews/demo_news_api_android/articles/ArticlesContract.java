package com.demonews.demo_news_api_android.articles;

import com.demonews.demo_news_api_android.BasePresenter;
import com.demonews.demo_news_api_android.BaseView;

/**
 * Created by james on 4/22/2017.
 */

public interface ArticlesContract {
    interface View extends BaseView<Presenter>{
        boolean isActive();
        void showAuthenticationActivity();

        void setUsername(String name);
        void setPhotoUrl(String url);
    }

    interface Presenter extends BasePresenter{
        void showArticles();
        void initiliazeFirebaseAuth();

        void signOut();
    }
}
