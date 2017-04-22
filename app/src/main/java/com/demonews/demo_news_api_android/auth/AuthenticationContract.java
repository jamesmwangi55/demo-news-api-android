package com.demonews.demo_news_api_android.auth;

import com.demonews.demo_news_api_android.BasePresenter;
import com.demonews.demo_news_api_android.BaseView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by james on 4/22/2017.
 */

public interface AuthenticationContract {
    interface View extends BaseView<Presenter>{

        void showAuthenticationFailed();
        void showArticlesActivity();
        boolean isActive();
    }

    interface Presenter extends BasePresenter{
        void firebaseAuthWithGoogleAccount(GoogleSignInAccount acct);
    }
}
