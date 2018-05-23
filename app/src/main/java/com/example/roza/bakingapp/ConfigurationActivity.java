package com.example.roza.bakingapp;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.roza.bakingapp.Adapters.ConfigureAdapter;
import com.example.roza.bakingapp.models.Recipe;
import com.example.roza.bakingapp.utils.JsonUtils;
import com.example.roza.bakingapp.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hiddenpik on 22.05.2018.
 */

public class ConfigurationActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Recipe>> {


    private int appWidgetId;
//    @BindView(R.id.nutella_button) Button nutellaButton;
//    @BindView(R.id.brownies_button) Button browniesButton;
//    @BindView(R.id.yellow_button) Button yellowButton;
//    @BindView(R.id.cheesecake_button) Button cheesecakeButton;

    @BindView(R.id.configure_widget_lv)
    ListView configureListView;


    //ArrayAdapter<>

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);



        ButterKnife.bind(this);

        getSupportLoaderManager().initLoader(0, null, this).forceLoad();
       // getLoaderManager().initLoader(0, null, this).forceLoad();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            // nutellaButton.setOnClickListener(this);

        }


    }
    /*
    I used widget tutorial from this webpage:
    https://laaptu.wordpress.com/2013/07/24/populate-appwidget-listview-with-remote-datadata-from-web/#
    */



    private void startWidget() {
        Intent intent = new Intent();
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                appWidgetId);
        setResult(Activity.RESULT_OK, intent);

        Intent serviceIntent = new Intent(this, BakingRemoteViewService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        startService(serviceIntent);

        this.finish();
    }

    @Override
    public Loader<ArrayList<Recipe>> onCreateLoader(int id, Bundle args) {
        Context context = ConfigurationActivity.this;

        return new AsyncTaskLoader<ArrayList<Recipe>>(context) {

            ArrayList<Recipe> parsedList;

            @Override
            public ArrayList<Recipe> loadInBackground() {
                Log.d("ConfigurationActivity", "Load recipes in background");
                URL recipeUrl = NetworkUtils.buildURL();

                Log.i("ConfigurationActivity", "recipeUrl" + recipeUrl);

                try {
                    String jsonResponse = NetworkUtils.getResponseFromHttpUrl(recipeUrl);
                    parsedList = JsonUtils.parseJson(jsonResponse);
                    return parsedList;

                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };


    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Recipe>> loader, ArrayList<Recipe> data) {
        if (data != null && !data.equals("")) {
            ConfigureAdapter adapter = new ConfigureAdapter(getApplicationContext(), data);
             configureListView.setAdapter(adapter);




        }
    }

        @Override
        public void onLoaderReset (Loader < ArrayList < Recipe >> loader) {

        }
    }

