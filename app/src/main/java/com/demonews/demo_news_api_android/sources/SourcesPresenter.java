package com.demonews.demo_news_api_android.sources;

import android.content.Context;
import android.support.annotation.NonNull;

import com.demonews.demo_news_api_android.data.sources.SourcesDataSource;
import com.demonews.demo_news_api_android.data.sources.SourcesRepository;
import com.demonews.demo_news_api_android.data.sources.models.Source;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by james on 4/23/2017.
 */

public class SourcesPresenter implements SourcesContract.Presenter {

    private static final String TAG = SourcesPresenter.class.getSimpleName();

    @NonNull private final SourcesContract.View mView;
    private final Context mContext;
    private final SourcesRepository mSourcesRepository;

    private SourcesFilteringType mCurrentFiltering = SourcesFilteringType.ALL_SOURCES;

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
        if(mView.isActive()){
            getSources();
        }
    }

    @Override
    public void getSources() {
        mView.showProgress(true);
        mSourcesRepository.getSources(new SourcesDataSource.LoadSourcesCallback() {
            @Override
            public void onSourcesLoaded(List<Source> sources) {
                mView.showProgress(false);
                if(sources!=null && !sources.isEmpty()){
                    List<Source> sourcesToShow = new ArrayList<Source>();

                    // Filter sources based on the request type
                    for(Source source: sources){
                        switch (mCurrentFiltering){
                            case ALL_SOURCES:
                                sourcesToShow.add(source);
                                break;
                            case TECHNOLOGY:
                                if(SourcesFilteringType.TECHNOLOGY.source().equalsIgnoreCase(source.getCategory()))
                                    sourcesToShow.add(source);
                                break;
                            case BUSINESS:
                                if(SourcesFilteringType.BUSINESS.source().equalsIgnoreCase(source.getCategory()))
                                    sourcesToShow.add(source);
                                break;
                            case ENTERTAINMENT:
                                if(SourcesFilteringType.ENTERTAINMENT.source().equalsIgnoreCase(source.getCategory()))
                                    sourcesToShow.add(source);
                                break;
                            case GAMING:
                                if(SourcesFilteringType.GAMING.source().equalsIgnoreCase(source.getCategory()))
                                    sourcesToShow.add(source);
                                break;
                            case GENERAL:
                                if(SourcesFilteringType.GENERAL.source().equalsIgnoreCase(source.getCategory()))
                                    sourcesToShow.add(source);
                                break;
                            case MUSIC:
                                if(SourcesFilteringType.MUSIC.source().equalsIgnoreCase(source.getCategory()))
                                    sourcesToShow.add(source);
                                break;
                            case POLITICS:
                                if(SourcesFilteringType.POLITICS.source().equalsIgnoreCase(source.getCategory()))
                                    sourcesToShow.add(source);
                                break;
                            case SCIENCE_AND_NATURE:
                                if(SourcesFilteringType.SCIENCE_AND_NATURE.source().equalsIgnoreCase(source.getCategory()))
                                    sourcesToShow.add(source);
                                break;
                            case SPORT:
                                if(SourcesFilteringType.SPORT.source().equalsIgnoreCase(source.getCategory()))
                                    sourcesToShow.add(source);
                                break;
                            default:
                                sourcesToShow.add(source);

                        }
                    }

                    mView.showSources(sourcesToShow);
                } else {
                    mView.showSourcesNotLoaded();
                }

            }

            @Override
            public void onSourcesNotLoaded() {
                mView.showSourcesNotLoaded();
            }
        });
    }

    @Override
    public void setFiltering(SourcesFilteringType requestType) {
        mCurrentFiltering = requestType;
    }

    @Override
    public SourcesFilteringType getFiltering() {
        return mCurrentFiltering;
    }

}
