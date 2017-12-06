package com.nchenari.news;

import com.nchenari.news.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nchenari on 12/6/17.
 */

public class NewsStore {
    private static List<Article> newsArticles = new ArrayList<>();

    public static List<Article> getNewsArticles() {
        return newsArticles;
    }

    public static void setNewsArticles(List<Article> newsArticle) {
        NewsStore.newsArticles = newsArticle;
    }
}
