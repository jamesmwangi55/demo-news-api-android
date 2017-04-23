package com.demonews.demo_news_api_android.data;

import com.demonews.demo_news_api_android.data.articles.model.Article;
import com.demonews.demo_news_api_android.data.sources.models.Source;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by james on 4/22/2017.
 */

public interface NewsApiEndpointInterface {
    @GET("sources")
    Observable<List<Source>> getSources();

    @GET("articles")
    Observable<List<Article>> getArticles(@Query("source_id") String source, @Query("sortBy") String sortBy);
}
