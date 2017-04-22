package com.demonews.demo_news_api_android.articles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.demonews.demo_news_api_android.DemoNewsApplication;
import com.demonews.demo_news_api_android.R;
import com.demonews.demo_news_api_android.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesActivity extends AppCompatActivity {

    @Inject ArticlesPresenter mArticlesPresenter;
    @BindView(R.id.toolbar) Toolbar mToolbar;



    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, ArticlesActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);

        ArticlesFragment articlesFragment =
                (ArticlesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(articlesFragment == null){
            articlesFragment = ArticlesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    articlesFragment, R.id.contentFrame);
        }

        DaggerArticlesComponent.builder()
                .articlesPresenterModule(new ArticlesPresenterModule(articlesFragment, this))
                .sourcesRepositoryComponent(((DemoNewsApplication) getApplication()).getSourcesRepositoryComponent())
                .build()
                .inject(this);
    }
}