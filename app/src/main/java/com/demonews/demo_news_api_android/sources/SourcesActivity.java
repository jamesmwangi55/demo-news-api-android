package com.demonews.demo_news_api_android.sources;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.demonews.demo_news_api_android.R;
import com.demonews.demo_news_api_android.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourcesActivity extends AppCompatActivity {

    @Inject SourcesPresenter mSourcesPresenter;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, SourcesActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        SourcesFragment sourcesFragment =
                (SourcesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(sourcesFragment == null){
            sourcesFragment = SourcesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    sourcesFragment, R.id.contentFrame);
        }




    }
}
