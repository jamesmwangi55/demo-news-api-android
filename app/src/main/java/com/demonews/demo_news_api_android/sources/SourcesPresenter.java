package com.demonews.demo_news_api_android.sources;

import android.content.Context;
import android.support.annotation.NonNull;

import com.demonews.demo_news_api_android.data.sources.SourcesRepository;

import javax.inject.Inject;

/**
 * Created by james on 4/23/2017.
 */

public class SourcesPresenter implements SourcesContract.Presenter {

    private static final String TAG = SourcesPresenter.class.getSimpleName();

    @NonNull private final SourcesContract.View mView;
    private final Context mContext;
    private final SourcesRepository mSourcesRepository;

    @Inject
    SourcesPresenter(SourcesContract.View view, Context context, SourcesRepository sourcesRepository){
        mView = view;
        mContext = context;
        mSourcesRepository = sourcesRepository;
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

    }
}
