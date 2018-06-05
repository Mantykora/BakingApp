package com.example.roza.bakingapp;

import android.graphics.Movie;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.roza.bakingapp.Adapters.RecipeAdapter;
import com.example.roza.bakingapp.models.Recipe;
import com.example.roza.bakingapp.utils.JsonUtils;
import com.example.roza.bakingapp.utils.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{

    @BindView(R.id.recipe_names_rv)
    RecyclerView namesRecycleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecipesListFragment fragment = new RecipesListFragment();
        if (fragment == null) {
            fragmentTransaction.add(R.id.fragment_name_list, fragment);
            fragmentTransaction.commit();
        }
    }
}

