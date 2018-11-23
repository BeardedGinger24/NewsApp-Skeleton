package com.example.rkjc.news_app_2;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rkjc.news_app_2.data.NewsItem;
import com.example.rkjc.news_app_2.data.NewsItemViewModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private List<NewsItem> articles; // Cached copy of words
    final private ListItemClickListener mOnClickListener;

    private NewsItemViewModel viewModel;

    public NewsRecyclerViewAdapter( NewsItemViewModel viewModel, ListItemClickListener mOnClickListener){
        //this.context = context;
        this.viewModel = viewModel;
//        mInflater = LayoutInflater.from(context);
        this.mOnClickListener = mOnClickListener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_item2;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NewsViewHolder viewHolder = new NewsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( NewsViewHolder articleViewHolder, final int i) {
            articleViewHolder.bind(i);
    }

    void setNewsItems(List<NewsItem> newsItems){
        articles = newsItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(articles != null){
            return articles.size();
        } else {
            return 0;
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView mArticleImage;
        TextView mArticleTitle;
        TextView mArticleDescription;
//        TextView mArticleDate;


        public NewsViewHolder(View itemView) {
            super(itemView);

            mArticleImage = (ImageView) itemView.findViewById(R.id.article_image);
            mArticleTitle = (TextView) itemView.findViewById(R.id.tv_article_title);
            mArticleDescription = (TextView) itemView.findViewById(R.id.tv_article_description);
//            mArticleDate = (TextView) itemView.findViewById(R.id.tv_article_date);

            itemView.setOnClickListener(this);
        }

        void bind(int position){
            Uri imageLink = Uri.parse(articles.get(position).getUrlToImage());

            if(imageLink != null){
                Picasso.get().load(imageLink).into(mArticleImage);
            }

//            mArticleImage.setImageURI(imageLink);
            mArticleTitle.setText(articles.get(position).getTitle());
            mArticleDescription.setText(articles.get(position).getPublishedAt() + " . " +articles.get(position).getDescription());
//            mArticleDate.setText("Date: " + articles.get(position).getPublishedAt());
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

}
