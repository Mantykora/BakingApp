package com.example.roza.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.roza.bakingapp.models.Recipe;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {
    static Recipe recipe;

    public static final String DATA_FETCHED = "com.wordpress.laaptu.DATA_FETCHED";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

//        CharSequence widgetText = context.getString(R.string.appwidget_text);
//        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
//       // views.setTextViewText(R.id.ingredient_widget_tv, );
//       // views.set
//
//        ArrayList<Recipe.Ingredients> ingredients = recipe != null ? recipe.getIngredientsList() : null;
//
//        Intent remoteIntent = new Intent(context, BakingRemoteViewService.class);
//        remoteIntent.putExtra("ingredients", ingredients);
//        Log.d("BakingWIdget", "" + ingredients);
//        views.setRemoteAdapter(R.id.widget_ingredients_lv, remoteIntent);
//
//
//        // Intent to lunch MainActivity
//        //Intent intent = new Intent(context, MainActivity.class);
//        //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//
//        //views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
//
//
//
//        // Instruct the widget manager to update the widget,
//        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

//

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
//       if (intent.getAction().equals(DATA_FETCHED)) {
//            int appWidgetId = intent.getIntExtra(
//                    AppWidgetManager.EXTRA_APPWIDGET_ID,
//                    AppWidgetManager.INVALID_APPWIDGET_ID);
//            AppWidgetManager appWidgetManager = AppWidgetManager
//                    .getInstance(context);
//        //   RemoteViews remoteViews = updateWidgetListView(context, appWidgetId);
//           // appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
//           // Bundle b = intent.getBundleExtra("recipeParcel");
//
//
//
//             //recipe = b.getParcelable("recipe");
//
//        recipe = intent.getParcelableExtra("recipeParcel");
//            Log.d("BakingWidget.java", (recipe == null ? "null" : recipe.getRecipeName()));


//        }
//        int appWidgetId = intent.getIntExtra(
//                AppWidgetManager.EXTRA_APPWIDGET_ID,
//                AppWidgetManager.INVALID_APPWIDGET_ID);




    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

