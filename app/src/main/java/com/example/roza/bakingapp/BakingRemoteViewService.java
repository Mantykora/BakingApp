package com.example.roza.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.roza.bakingapp.models.Recipe;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class BakingRemoteViewService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewFactory(this.getApplicationContext(), intent);
    }

    class ListViewFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context context;
        private Intent intent;
        private ArrayList<Recipe.Ingredients> ingredients;

        public ListViewFactory(Context applicationContext, Intent intent) {
            context = applicationContext;
            this.intent = intent;

            Bundle b = intent.getParcelableExtra("b");
            ingredients = b.getParcelableArrayList("ingredientsParcel");


        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {


        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (ingredients != null && !ingredients.isEmpty()) {
                return ingredients.size();
            } else {
                return 0;
            }
        }

        @Override
        public RemoteViews getViewAt(int position) {


            Recipe.Ingredients ingredient = ingredients.get(position);

            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
            rv.setTextViewText(R.id.ingredient_widget_tv, ingredient.getIngredient());
            rv.setTextViewText(R.id.quantity_measure_tv, String.valueOf(ingredient.getIngredientQuantity()));
            rv.setTextViewText(R.id.measure_widget_tv, ingredient.getIgredientMeasure());

            return rv;

        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
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
