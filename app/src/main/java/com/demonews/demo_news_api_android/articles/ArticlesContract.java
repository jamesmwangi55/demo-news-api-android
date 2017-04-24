package com.demonews.demo_news_api_android.articles;

import com.demonews.demo_news_api_android.BasePresenter;
import com.demonews.demo_news_api_android.BaseView;
import com.demonews.demo_news_api_android.data.articles.model.Article;
import com.demonews.demo_news_api_android.data.sources.models.Source;

import java.util.List;

/**
 * Created by james on 4/22/2017.
 */

public interface ArticlesContract {
    interface View extends BaseView<Presenter>{
        boolean isActive();
        void showAuthenticationActivity();
        void setUsername(String name);
        void showProgress(boolean show);
        void setPhotoUrl(String url);
        void onSourcesLoaded(List<Source> sources);
        void onSourcesNotLoaded();
        void onArticlesLoaded(List<Article> articles);
        void onArticlesNotLoaded();

        void showArticlesOnLoaded(List<Article> articles);

        void showArticlesNotLoaded();
    }

    interface Presenter extends BasePresenter{
        void showArticles(String source, String category);
        void initiliazeFirebaseAuth();
        void signOut();
    }
}
