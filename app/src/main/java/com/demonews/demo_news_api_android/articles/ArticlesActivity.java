package com.demonews.demo_news_api_android.articles;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.demonews.demo_news_api_android.SingleFragmentActivity;

import javax.inject.Inject;

public class ArticlesActivity extends SingleFragmentActivity {

    @Inject ArticlesPresenter mArticlesPresenter;
//    @BindView(R.id.toolbar) Toolbar mToolbar;
//    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
//    @BindView(R.id.nav_view) NavigationView mNavigationView;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, ArticlesActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return ArticlesFragment.newInstance();
    }


//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_articles);
//        ButterKnife.bind(this);
//
//        setSupportActionBar(mToolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//
//        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
//
//        if(mNavigationView != null){
//            setUpDrawerContent(mNavigationView);
//        }
//
//
//        ArticlesFragment articlesFragment =
//                (ArticlesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//
//        if(articlesFragment == null){
//            articlesFragment = ArticlesFragment.newInstance();
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
//                    articlesFragment, R.id.contentFrame);
//        }
//
//        DaggerArticlesComponent.builder()
//                .articlesPresenterModule(new ArticlesPresenterModule(articlesFragment, this))
//                .articlesRepositoryComponent(((DemoNewsApplication)getApplication()).getArticlesRepositoryComponent())
//                .build()
//                .inject(this);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                //open the navigation drawer when the home icon is selected from the drawer.
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void setUpDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_articles:
//                        // Do nothing, we are already on that screen
//                        break;
//                    case R.id.action_sources:
//                        startActivity(SourcesActivity.newIntent(ArticlesActivity.this));
//                        break;
//                    default:
//                        break;
//                }
//
//                item.setChecked(true);
//                mDrawerLayout.closeDrawers();
//                return true;
//            }
//        });
//    }


}