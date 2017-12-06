package com.nchenari.news.networking;

import com.nchenari.news.model.GetArticlesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nchenari on 12/6/17.
 */

public class NewsAPI {
    private static final String APIKEY = "277364822b97433ebb96c7413e3036a0";
    private static final String APIPATH = "https://newsapi.org/v2/";

    private static NewsService newsService = null;

    public static NewsService getApi() {
        if(newsService == null) {
            // initialize NewsService
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);
        }
        return newsService;
    }

    public interface NewsService {
        @GET("top-headlines?sources=the-verge&apiKey=" + APIKEY)
        Call<GetArticlesResponse> getArticles();

    }
}
