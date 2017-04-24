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

public class FilteredArticlesActivity extends AppCompatActivity {

    private static final String TAG = FilteredArticlesActivity.class.getSimpleName();
    private static final String EXTRA_CATEGORY = "category_extra";
    private static final String EXTRA_SOURCE = "source_extra";

    @Inject ArticlesPresenter mArticlesPresenter;
    private String mCategory;
    private String mSource;

    @BindView(R.id.toolbar) Toolbar mToolbar;

    public static Intent newIntent(Context context, String category, String source){
        Intent intent = new Intent(context, FilteredArticlesActivity.class);

        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_SOURCE, source);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_articles);
        ButterKnife.bind(this);

        if(savedInstanceState!=null){
            mSource = savedInstanceState.getString(EXTRA_SOURCE);
            mCategory = savedInstanceState.getString(EXTRA_CATEGORY);
        } else {
            mSource = getIntent().getStringExtra(EXTRA_SOURCE);
            mCategory = getIntent().getStringExtra(EXTRA_CATEGORY);
        }

        // Set up the toolbar.
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        ArticlesFragment articlesFragment =
                (ArticlesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(articlesFragment == null){
            articlesFragment = ArticlesFragment.newInstance(mCategory, mSource);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), articlesFragment, R.id.contentFrame);
        }

        DaggerArticlesComponent.builder()
                .articlesRepositoryComponent(((DemoNewsApplication)getApplication()).getArticlesRepositoryComponent())
                .articlesPresenterModule(new ArticlesPresenterModule(articlesFragment, this))
                .build()
                .inject(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(EXTRA_CATEGORY, mCategory);
        outState.putString(EXTRA_SOURCE, mSource);
        super.onSaveInstanceState(outState);
    }
}
