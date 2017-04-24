package com.demonews.demo_news_api_android.sources;

import com.demonews.demo_news_api_android.BasePresenter;
import com.demonews.demo_news_api_android.BaseView;
import com.demonews.demo_news_api_android.data.sources.models.Source;

import java.util.List;

/**
 * Created by james on 4/23/2017.
 */

public interface SourcesContract{
    interface View extends BaseView<Presenter> {
        boolean isActive();
        void showSources(List<Source> sources);
        void showSourcesNotLoaded();
        void showProgress(boolean show);
    }

    interface Presenter extends BasePresenter{
        void getSources();
        void setFiltering(SourcesFilteringType requestType);
        SourcesFilteringType getFiltering();
    }
}
