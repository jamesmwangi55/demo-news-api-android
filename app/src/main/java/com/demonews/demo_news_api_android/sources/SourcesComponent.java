package com.demonews.demo_news_api_android.sources;

import com.demonews.demo_news_api_android.data.sources.SourcesRepositoryComponent;
import com.demonews.demo_news_api_android.util.FragmentScoped;

import dagger.Component;

/**
 * Created by james on 4/23/2017.
 */
@FragmentScoped
@Component(dependencies = SourcesRepositoryComponent.class,
        modules = SourcesPresenterModule.class)
public interface SourcesComponent {
    void inject(SourcesFragment sourcesFragment);
}
