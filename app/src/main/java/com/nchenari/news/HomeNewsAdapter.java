package com.nchenari.news;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.nchenari.news.model.Article;
import com.nchenari.news.utils.DateUtils;

import java.util.List;

/**
 * Created by nchenari on 12/6/17.
 */

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder> {

    private List<Article> Articles;

    public HomeNewsAdapter(List<Article> Articles) {
        this.Articles = Articles;
    }

    @Override
    public HomeNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false);
        HomeNewsViewHolder homeNewsViewHolder = new HomeNewsViewHolder(view);

        return homeNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(HomeNewsViewHolder holder, final int position) {
        Article newsArticle = Articles.get(position);
        // For image loading...network calls to download the image, loading, recycling etc.
        Glide.with(holder.cardImageView.getContext()).load(Articles.get(position).getUrlToImage())
                .centerCrop()
                .into(holder.cardImageView);
        holder.cardTitleTextView.setText(newsArticle.getTitle());
        holder.cardTimeTextView.setText(DateUtils.formatNewsApiDate(newsArticle.getPublishedAt()));
        holder.cardContentTextView.setText(newsArticle.getDescription());
        // itemView is baseView from holder
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // log analytics
                FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(v.getContext());
                Bundle bundle = new Bundle();
                bundle.putString("index", String.valueOf(position));
                firebaseAnalytics.logEvent("cardClicked", bundle);

                NewsDetailsActivity.launch(v.getContext(), position);
            }
        });

    }

    @Override
    public int getItemCount() {
        // TODO: Implement null pointer check
        return Articles.size();
    }

    // Class for ViewHolder
    public static class HomeNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImageView;
        TextView cardTitleTextView;
        TextView cardTimeTextView;
        TextView cardContentTextView;


        public HomeNewsViewHolder(View itemView) {
            super(itemView);
            cardImageView = itemView.findViewById(R.id.card_news_image);
            cardTitleTextView = itemView.findViewById(R.id.card_news_title);
            cardTimeTextView = itemView.findViewById(R.id.card_news_time);
            cardContentTextView = itemView.findViewById(R.id.card_news_content);
        }

    }
}
