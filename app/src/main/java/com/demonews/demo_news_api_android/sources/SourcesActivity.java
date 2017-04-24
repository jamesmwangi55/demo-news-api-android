package com.demonews.demo_news_api_android.sources;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.demonews.demo_news_api_android.SingleFragmentActivity;

public class SourcesActivity extends SingleFragmentActivity {

//    @Inject SourcesPresenter mSourcesPresenter;
//    @BindView(R.id.toolbar) Toolbar mToolbar;
//    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
//    @BindView(R.id.nav_view) NavigationView mNavigationView;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, SourcesActivity.class);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return SourcesFragment.newInstance();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sources);
//
//        ButterKnife.bind(this);
//
//        setSupportActionBar(mToolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//
//        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
//        if(mNavigationView != null){
//            setDrawerContent(mNavigationView);
//        }
//
//        SourcesFragment sourcesFragment =
//                (SourcesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//
//        if(sourcesFragment == null){
//            sourcesFragment = SourcesFragment.newInstance();
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
//                    sourcesFragment, R.id.contentFrame);
//        }
//
//        // create the presenter
//        DaggerSourcesComponent.builder()
//                .sourcesRepositoryComponent(((DemoNewsApplication) getApplication()).getSourcesRepositoryComponent())
//                .sourcesPresenterModule(new SourcesPresenterModule(sourcesFragment, this))
//                .build().inject(this);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                // open the drawer navigation when the home button  is selected from the toolbar
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void setDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_sources:
//                        // Do nothing we are already on that screen
//                        break;
//                    case R.id.action_articles:
//                        startActivity(ArticlesActivity.newIntent(SourcesActivity.this));
//                        break;
//                    default:
//                        break;
//                }
//                // Close the navigation drawer when an item is selected
//                item.setChecked(true);
//                mDrawerLayout.closeDrawers();
//                return true;
//            }
//        });
//    }
}
