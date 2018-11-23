package com.example.rkjc.news_app_2.sync;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.util.Log;

import com.example.rkjc.news_app_2.MainActivity;
import com.example.rkjc.news_app_2.data.NewsItem;
import com.example.rkjc.news_app_2.data.NewsItemRepository;
import com.example.rkjc.news_app_2.data.NewsItemViewModel;

import com.example.rkjc.news_app_2.utils.NotificationUtils;

public class UpdateTask {
//    MainActivity mainActivity = new MainActivity();
//    NewsItemRepository newsItemRepository = new NewsItemRepository();

    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static final String UPDATE_NEWS = "update_news";
//    public static final String ACTION_OPEN_APP = "open-app";

    public static void executeTask(Context context, String action){
        if(ACTION_DISMISS_NOTIFICATION.equals(action)){
            NotificationUtils.clearAllNotifications(context);
        } else if(UPDATE_NEWS.equals(action)){
            Log.d("Updatetask", "executeTask: ");
            UpdateTask instance = new UpdateTask();
            instance.update();
            NotificationUtils.notifyNews(context);
        }

    }

        public void update(){
//            mainActivity.sketch();
//            newsItemRepository.makeNewsSearchQuery();
        }
}
