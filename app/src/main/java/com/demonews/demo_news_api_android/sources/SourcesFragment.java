package com.demonews.demo_news_api_android.sources;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demonews.demo_news_api_android.R;
import com.demonews.demo_news_api_android.data.sources.models.Source;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends Fragment implements SourcesContract.View{

    private SourcesContract.Presenter mPresenter;

    private Unbinder mUnbinder;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sources, container, false);
        mUnbinder = ButterKnife.bind(this, view);
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
    public void setPresenter(@NonNull SourcesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showSources(List<Source> sources) {
        Toast.makeText(getActivity(), "Sources loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSourcesNotLoaded() {
        Toast.makeText(getActivity(), R.string.sources_loading_failed, Toast.LENGTH_SHORT).show();
    }
}
