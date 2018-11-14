package com.example.rkjc.news_app_2.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rkjc.news_app_2.JsonUtils;
import com.example.rkjc.news_app_2.MainActivity;
import com.example.rkjc.news_app_2.NetworkUtils;
import com.example.rkjc.news_app_2.NewsRecyclerViewAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsItemRepository {

    private NewsItemDao mNewsItemsDao;
    private LiveData<List<NewsItem>> mAllNewsItems;
    private NewsItemViewModel newsItemViewModel;
    NewsRecyclerViewAdapter mNewsAdapter;
    List<NewsItem> articles;

    public NewsItemRepository(Application application){
        NewsItemDatabase db = NewsItemDatabase.getDatabase(application.getApplicationContext());
        mNewsItemsDao = db.newsItemDao();
        mAllNewsItems = mNewsItemsDao.getAllNewsItems();
    }

    LiveData<List<NewsItem>> getAllWords() {
        return mAllNewsItems;
    }

    public void insert (List<NewsItem> newsItems) {
        new insertAsyncTask(mNewsItemsDao).execute(newsItems);
    }

    public void delete(){
        new deleteAsyncTask(mNewsItemsDao).execute();
    }
//
//    public void get (){
//        new getAsyncTask(mNewsItemsDao).execute();
//    }
//
//    private static class getAsyncTask extends AsyncTask<List<NewsItem>, Void, Void>{
//        private NewsItemDao mAsyncTaskDao;
//        getAsyncTask(NewsItemDao dao) {mAsyncTaskDao = dao;}
//
//        @Override
//        protected Void doInBackground(List<NewsItem>... lists) {
//            mAsyncTaskDao.getAllNewsItems();
//            return null;
//        }
//    }

    private static class insertAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {
        private NewsItemDao mAsyncTaskDao;
        insertAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<NewsItem>... params) {
            for(int i = 0; i < params.length; i++){
                mAsyncTaskDao.insert(params[i]);
            }
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {
        private NewsItemDao mAsyncTaskDao;
        deleteAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<NewsItem>... params) {
//            Log.d("mycode", "deleteding word: " + params[0].get(0).getAuthor());
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void makeNewsSearchQuery(){
        URL newsSearchUrl = NetworkUtils.buildUrl();
        new NewsQueryTask().execute(newsSearchUrl);
    }

    public class NewsQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            delete();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String newsSearchResults = null;
            try {
                newsSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsSearchResults;
        }

        @Override
        protected void onPostExecute(String newsSearchResults) {
            super.onPostExecute(newsSearchResults);
            articles = JsonUtils.parseNews(newsSearchResults);
            insert(articles);
        }
    }
}