package com.demonews.demo_news_api_android.sources;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
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
import com.demonews.demo_news_api_android.articles.FilteredArticlesActivity;
import com.demonews.demo_news_api_android.data.sources.models.Source;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.ALL_SOURCES;
import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.BUSINESS;
import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.ENTERTAINMENT;
import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.GAMING;
import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.GENERAL;
import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.MUSIC;
import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.POLITICS;
import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.SCIENCE_AND_NATURE;
import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.SPORT;
import static com.demonews.demo_news_api_android.sources.SourcesFilteringType.TECHNOLOGY;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends Fragment implements SourcesContract.View{

    @Inject SourcesPresenter mSourcesPresenter;
    private SourcesContract.Presenter mPresenter;


    private Unbinder mUnbinder;

    @BindView(R.id.recylerView) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindInt(R.integer.grid_items) int gridItems;
    private SourcesAdapter mSourcesAdapter;
    private String mCategory;

    public static SourcesFragment newInstance() {

        Bundle args = new Bundle();

        SourcesFragment fragment = new SourcesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SourcesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mCategory = "";
        mSourcesAdapter = new SourcesAdapter(new ArrayList<Source>(0));
                // create the presenter
        DaggerSourcesComponent.builder()
                .sourcesRepositoryComponent(((DemoNewsApplication) getActivity().getApplication()).getSourcesRepositoryComponent())
                .sourcesPresenterModule(new SourcesPresenterModule(this, getActivity()))
                .build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sources, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), gridItems));
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
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_resources, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()){
            case R.id.action_filter:
                showFilteringPopUpMenu();
                break;
            default:
        }
        
        return true;
    }

    private void showFilteringPopUpMenu() {
        PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.action_filter));
        popup.getMenuInflater().inflate(R.menu.filter_sources, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.filter_all:
                        mPresenter.setFiltering(ALL_SOURCES);
                        mCategory = ALL_SOURCES.source();
                        break;
                    case R.id.filter_business:
                        mPresenter.setFiltering(BUSINESS);
                        mCategory = BUSINESS.source();
                        break;
                    case R.id.filter_entertainment:
                        mPresenter.setFiltering(ENTERTAINMENT);
                        mCategory = ENTERTAINMENT.source();
                        break;
                    case R.id.filter_gaming:
                        mPresenter.setFiltering(GAMING);
                        mCategory = GAMING.source();
                        break;
                    case R.id.filter_general:
                        mPresenter.setFiltering(GENERAL);
                        mCategory = GENERAL.source();
                        break;
                    case R.id.filter_music:
                        mPresenter.setFiltering(MUSIC);
                        mCategory = MUSIC.source();
                        break;
                    case R.id.filter_politics:
                        mPresenter.setFiltering(POLITICS);
                        mCategory = POLITICS.source();
                        break;
                    case R.id.filter_science_and_nature:
                        mPresenter.setFiltering(SCIENCE_AND_NATURE);
                        mCategory = SCIENCE_AND_NATURE.source();
                        break;
                    case R.id.filter_sports:
                        mPresenter.setFiltering(SPORT);
                        mCategory = SPORT.source();
                        break;
                    case R.id.filter_technology:
                        mPresenter.setFiltering(TECHNOLOGY);
                        mCategory = TECHNOLOGY.source();
                        break;
                }

                mPresenter.getSources();
                return true;
            }
        });

        popup.show();
    }

    @Override
    public void setPresenter(@NonNull SourcesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showSources(List<Source> sources) {
        if(isAdded()){
            mSourcesAdapter.replaceData(sources);
            mRecyclerView.setAdapter(mSourcesAdapter);
        }
    }

    @Override
    public void showSourcesNotLoaded() {
        if(isAdded())
        Toast.makeText(getActivity(), R.string.sources_loading_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean show) {
        if(isAdded()){
            mRecyclerView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    class SourcesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.source) TextView mSourceTextView;
        @BindView(R.id.category) TextView mCategoryTextView;
        private Source mSource = new Source();

        public SourcesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindSource(Source source) {
            mSource = source;
            mSourceTextView.setText(source.getName());
            mCategoryTextView.setText(source.getCategory().substring(0, 1).toUpperCase() + source.getCategory().substring(1));
        }

        @Override
        public void onClick(View v) {
            startActivity(FilteredArticlesActivity.newIntent(getActivity(), mSource.getCategory(), mSource.getSourceId()));
        }

    }

    private class SourcesAdapter extends RecyclerView.Adapter<SourcesHolder>{

        private List<Source> mSources = new ArrayList<>();

        public SourcesAdapter(List<Source> sources) {
            setList(sources);
        }

        private void setList(List<Source> sources) {
            mSources = checkNotNull(sources);
        }

        public void replaceData(List<Source> sources){
            setList(sources);
            notifyDataSetChanged();
        }

        @Override
        public SourcesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.item_source, parent, false);
            return new SourcesHolder(view);
        }

        @Override
        public void onBindViewHolder(SourcesHolder holder, int position) {
            Source source = mSources.get(position);
            holder.bindSource(source);
        }

        @Override
        public int getItemCount() {
            return mSources.size();
        }
    }
}
