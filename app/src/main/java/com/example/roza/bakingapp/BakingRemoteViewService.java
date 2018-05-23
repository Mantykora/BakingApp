package com.example.roza.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.roza.bakingapp.models.Recipe;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by hiddenpik on 21.05.2018.
 */

public class BakingRemoteViewService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewFactory(this.getApplicationContext());
    }

    class ListViewFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context context;
        private ArrayList<Recipe.Ingredients> ingredients;

        public ListViewFactory(Context applicationContext) {
            context = applicationContext;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//            String json = preferences.getString("shared_preff", "");
//            if (!json.equals("")) {
//                Gson gson = new Gson();
//                ingredients = gson.fromJson(json, )
//            }



        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int i) {
            return null;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }



    }
}
