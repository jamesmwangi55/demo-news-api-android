package com.demonews.demo_news_api_android.data.sources;

import android.support.annotation.NonNull;

import com.demonews.demo_news_api_android.data.sources.models.Source;

import java.util.List;

/**
 * Created by james on 4/22/2017.
 */

public interface SourcesDataSource {
    interface LoadSourcesCallback{
        void onSourcesLoaded(List<Source> sources);
        void onSourcesNotLoaded();
    }

    void getSources(@NonNull LoadSourcesCallback callback);

}
