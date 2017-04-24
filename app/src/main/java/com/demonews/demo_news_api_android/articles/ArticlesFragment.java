package com.demonews.demo_news_api_android.articles;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.demonews.demo_news_api_android.DemoNewsApplication;
import com.demonews.demo_news_api_android.R;
import com.demonews.demo_news_api_android.auth.AuthenticationActivity;
import com.demonews.demo_news_api_android.customtabs.CustomTabActivityHelper;
import com.demonews.demo_news_api_android.data.articles.model.Article;
import com.demonews.demo_news_api_android.data.sources.models.Source;
import com.facebook.drawee.view.SimpleDraweeView;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment implements ArticlesContract.View,
        GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = ArticlesFragment.class.getSimpleName();

    private static final String ARGS_CATEGORY = "category_extra";
    private static final String ARGS_SOURCE = "source_extra";

    private ArticlesContract.Presenter mPresenter;
    private static final int RC_SIGN_IN = 9001;

    @Inject
    ArticlesPresenter mArticlesPresenter;

    @BindView(R.id.recylerView) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    private ArticleAdapter mArticleAdapter;
    CustomTabsIntent mCustomTabsIntent = new CustomTabsIntent.Builder().build();

    private Unbinder mUnbinder;

    private String mUsername;
    private String mPhotoUrl;
    private GoogleApiClient mGoogleApiClient;
    public static final String ANONYMOUS = "anonymous";
    private String mCategory;
    private String mSource;


    public ArticlesFragment() {
        // Required empty public constructor
    }

    public static ArticlesFragment newInstance() {

        Bundle args = new Bundle();

        ArticlesFragment fragment = new ArticlesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ArticlesFragment newInstance(String category, String source) {
        Bundle args = new Bundle();
        args.putString(ARGS_CATEGORY, category);
        args.putString(ARGS_SOURCE, source);
        ArticlesFragment fragment = new ArticlesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        mUsername = ANONYMOUS;

        if(savedInstanceState!=null){
            mCategory = savedInstanceState.getString(ARGS_CATEGORY);
            mSource = savedInstanceState.getString(ARGS_SOURCE);
        } else {
            mSource = getArguments().getString(ARGS_SOURCE);
            mCategory = getArguments().getString(ARGS_CATEGORY);
        }

        DaggerArticlesComponent.builder()
                .articlesPresenterModule(new ArticlesPresenterModule(this, getActivity()))
                .articlesRepositoryComponent(((DemoNewsApplication)getActivity().getApplication()).getArticlesRepositoryComponent())
                .build()
                .inject(this);


        mArticleAdapter = new ArticleAdapter(new ArrayList<Article>(0));

        if(mGoogleApiClient != null){
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API)
                    .build();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGS_CATEGORY, mCategory);
        outState.putString(ARGS_SOURCE, mSource);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        mUnbinder = ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setItemViewCacheSize(20);
//        mRecyclerView.setDrawingCacheEnabled(true);
//        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        if(isAdded()){
            mPresenter.showArticles(mSource, mCategory);
        }
    }

    @Override
    public void setPresenter(@NonNull ArticlesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showAuthenticationActivity() {
        startActivity(AuthenticationActivity.newIntent(getActivity()));
        getActivity().finish();
    }

    @Override
    public void setUsername(String name) {
        mUsername = name;
    }

    @Override
    public void showProgress(boolean show) {
        if(mProgressBar!=null){
            mRecyclerView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void setPhotoUrl(String url) {
        if(url != null){
            mPhotoUrl = url;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(getActivity(), R.string.google_play_service_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_articles, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_signout:
                AuthUI.getInstance()
                        .signOut(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(AuthenticationActivity.newIntent(getActivity()));
                                getActivity().finish();
                            }
                        });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onSourcesLoaded(List<Source> sources) {
        Toast.makeText(getActivity(), "Sources Loaded", Toast.LENGTH_SHORT)
                .show();

    }

    @Override
    public void onSourcesNotLoaded() {
        Toast.makeText(getActivity(), R.string.load_sources_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onArticlesLoaded(List<Article> articles) {

    }

    @Override
    public void onArticlesNotLoaded() {

    }

    @Override
    public void showArticlesOnLoaded(List<Article> articles) {
        if(isAdded()){
            mArticleAdapter.replaceData(articles);
            mRecyclerView.setAdapter(mArticleAdapter);
        }
    }

    @Override
    public void showArticlesNotLoaded() {
        Toast.makeText(getActivity(), R.string.artiles_loading_failed, Toast.LENGTH_SHORT).show();
    }



    class ArticleHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.imageView) SimpleDraweeView mSimpleDraweeView;
        @BindView(R.id.title) TextView mTitleTextView;
        @BindView(R.id.category) TextView mCategoryTextView;
        @BindView(R.id.source) TextView mSourceTextView;
        private Article mArticle;


        public ArticleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindArticle(Article article){
            mArticle = article;
            if(article.getUrlToImage()!=null && !article.getUrlToImage().isEmpty()){
                Uri uri = Uri.parse(article.getUrlToImage());
                mSimpleDraweeView.setImageURI(uri);
            }

            mTitleTextView.setText(article.getTitle());
            mCategoryTextView.setText(article.getCategory().substring(0, 1).toUpperCase() + article.getCategory().substring(1));
            mSourceTextView.setText(article.getName());
        }

        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse(mArticle.getUrl());
            CustomTabActivityHelper.openCustomTab(getActivity(), mCustomTabsIntent, uri, new CustomTabActivityHelper.CustomTabFallback() {
                @Override
                public void openUri(Activity activity, Uri uri) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    activity.startActivity(intent);
                }
            });
        }
    }

    class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder>{

        private List<Article> mArticles = new ArrayList<>();

        public ArticleAdapter(List<Article> articles){
            setList(articles);
        }

        private void setList(List<Article> articles) {
            mArticles = checkNotNull(articles);
        }

        public void replaceData(List<Article> articles){
            setList(articles);
            notifyDataSetChanged();
        }

        @Override
        public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_article, parent, false);
            return new ArticleHolder(view);
        }

        @Override
        public void onBindViewHolder(ArticleHolder holder, int position) {
            Article article = mArticles.get(position);
            holder.bindArticle(article);
        }

        @Override
        public int getItemCount() {
            return mArticles.size();
        }
    }
}
