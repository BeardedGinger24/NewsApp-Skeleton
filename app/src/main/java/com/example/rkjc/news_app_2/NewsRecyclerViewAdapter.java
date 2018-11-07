package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    //Context context;
    private ArrayList<NewsItem> articles;
    final private ListItemClickListener mOnClickListener;


    public NewsRecyclerViewAdapter(ArrayList<NewsItem> articles, ListItemClickListener mOnClickListener){
        //this.context = context;
        this.articles = articles;
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
    public void onBindViewHolder( NewsViewHolder articleViewHolder, int i) {
        articleViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return articles.size();
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
