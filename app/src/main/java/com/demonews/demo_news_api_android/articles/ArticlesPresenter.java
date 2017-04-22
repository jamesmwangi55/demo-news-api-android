package com.demonews.demo_news_api_android.articles;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

/**
 * Created by james on 4/22/2017.
 */

public class ArticlesPresenter implements ArticlesContract.Presenter {


    @NonNull
    private final ArticlesContract.View mView;
    private final Context mContext;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;



    @Inject
    ArticlesPresenter(ArticlesContract.View view, Context context){
        mView = view;
        mContext = context;
    }

    /**
     * Method injection is used here to safely reference {@code this} after the object is created.
     */
    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        initiliazeFirebaseAuth();
    }

    @Override
    public void showArticles() {

    }

    @Override
    public void initiliazeFirebaseAuth() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if(mFirebaseUser == null){
            // Not signed in, launch the AuthenticationActivity
            mView.showAuthenticationActivity();
            return;
        } else {
            mView.setUsername(mFirebaseUser.getDisplayName());
//            mView.setPhotoUrl(mFirebaseUser.getPhotoUrl().toString());
        }
    }

    @Override
    public void signOut() {
        mFirebaseAuth.signOut();
    }
}
