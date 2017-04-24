package com.demonews.demo_news_api_android.articles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.demonews.demo_news_api_android.data.articles.ArticlesDataSource;
import com.demonews.demo_news_api_android.data.articles.ArticlesRepository;
import com.demonews.demo_news_api_android.data.articles.model.Article;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by james on 4/22/2017.
 */

public class ArticlesPresenter implements ArticlesContract.Presenter {

    private static final String TAG = ArticlesPresenter.class.getSimpleName();


    @NonNull
    private final ArticlesContract.View mView;
    private final Context mContext;
    private final ArticlesRepository mArticlesRepository;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Inject
    ArticlesPresenter(ArticlesContract.View view, Context context, ArticlesRepository articlesRepository){
        mView = view;
        mContext = context;
        mArticlesRepository = articlesRepository;
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
        showArticles();
    }

    @Override
    public void showArticles() {
        mView.showProgress(true);
        mArticlesRepository.getArticles(new ArticlesDataSource.LoadArticlesCallback() {

            @Override
            public void onArticlesLoaded(List<Article> articles) {
                Log.i(TAG, "onArticlesLoaded: ");
                mView.showProgress(false);
                if(articles != null && !articles.isEmpty()){
                    mView.showArticlesOnLoaded(articles);
                } else {
                    mView.showArticlesNotLoaded();
                }

            }

            @Override
            public void onArticlesNotLoaded() {
                mView.showProgress(false);
                mView.showArticlesNotLoaded();
                Log.d(TAG, "onArticlesNotLoaded: Articles not loaded");
            }
        });
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
