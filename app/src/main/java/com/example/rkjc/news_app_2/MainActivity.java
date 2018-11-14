package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rkjc.news_app_2.data.NewsItem;
import com.example.rkjc.news_app_2.data.NewsItemRepository;
import com.example.rkjc.news_app_2.data.NewsItemViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NewsRecyclerViewAdapter.ListItemClickListener {

    RecyclerView mNewsArticles;

    NewsItemViewModel newsItemViewModel;
    NewsItemRepository newsItemRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsArticles = (RecyclerView) findViewById(R.id.news_recyclerview);
        newsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        final NewsRecyclerViewAdapter adapter = new NewsRecyclerViewAdapter( newsItemViewModel,
                MainActivity.this);
        mNewsArticles.setAdapter(adapter);
        mNewsArticles.setLayoutManager(new LinearLayoutManager(this));
        newsItemViewModel.getAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable final List<NewsItem> newsItems) {
                adapter.setNewsItems(newsItems);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNewsArticles.setLayoutManager(layoutManager);

        mNewsArticles.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            newsItemViewModel.update();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Uri web_page = Uri.parse(newsItemViewModel.getAllNewsItems().getValue().get(clickedItemIndex).getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, web_page);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
