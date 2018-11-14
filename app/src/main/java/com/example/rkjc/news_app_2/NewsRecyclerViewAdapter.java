package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rkjc.news_app_2.data.NewsItem;
import com.example.rkjc.news_app_2.data.NewsItemViewModel;

import java.io.IOException;
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
        int layoutIdForListItem = R.layout.news_item;
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

        TextView mArticleTitle;
        TextView mArticleDescription;
        TextView mArticleDate;


        public NewsViewHolder(View itemView) {
            super(itemView);

            mArticleTitle = (TextView) itemView.findViewById(R.id.tv_article_title);
            mArticleDescription = (TextView) itemView.findViewById(R.id.tv_article_description);
            mArticleDate = (TextView) itemView.findViewById(R.id.tv_article_date);

            itemView.setOnClickListener(this);
        }

        void bind(int position){
            mArticleTitle.setText("Title: " + articles.get(position).getTitle());
            mArticleDescription.setText("Description: " +articles.get(position).getDescription());
            mArticleDate.setText("Date: " + articles.get(position).getPublishedAt());
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

}
