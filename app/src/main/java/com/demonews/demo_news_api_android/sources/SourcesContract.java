package com.demonews.demo_news_api_android.sources;

import com.demonews.demo_news_api_android.BasePresenter;
import com.demonews.demo_news_api_android.BaseView;

/**
 * Created by james on 4/23/2017.
 */

public interface SourcesContract{
    interface View extends BaseView<Presenter> {
        boolean isActive();
    }

    interface Presenter extends BasePresenter{

    }
}
