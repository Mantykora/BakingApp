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

public class MainActivity extends AppCompatActivity //implements LoaderCallbacks<ArrayList<Recipe>>
 {

    @BindView(R.id.recipe_names_rv)
    RecyclerView namesRecycleView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<Recipe> recipesList;


    //DONE 1 Extract JSON from URL
    //DONE 1.1 get data from URL AsyncTask or somtehing - done - test it on phone and add recyclerView and adapter etc on onloadfinished.
    //DONE 1.2 in JSON add to Recipe class wszystkie wyekstrahowane rzeczy
    //DONE 2 https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json
    //DONE 3 Create RecycleView for List
    //DONE 4 Show titles of recipes in List in MainActivity
    //DONE 5 Create fragment for steps

    //TODO 6 Create intent to this fragment
    //TODO 7 In fragment show Ingredients button and number of steps (everyone in a button or something)
    //TODO 8 Create fragment for ingredients
    //TODO 9 Show ingredients in a seperate fragment (intent from clicking “ingredients” button
    //TODO 10 Create fragment for a step
    //TODO 11 Learn Exoplayer
    //TODO 12 Show an exoplayer if is video in json
    //TODO 13 Show description if isn’t
    //TODO 14 Create layout for tablet
    //TODO 15 Figure out what this Espresso is
    //TODO 16 TLest with this Espresso
    // TODO 17 earn about widgets
    //TODO 18 Do a widget that displays ingredients list for desire recipe
    //TODO 19 Add navigation between each step
    // TODO 20 Add data binding library


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecipesListFragment fragment = new RecipesListFragment();
        fragmentTransaction.add(R.id.fragment_name_list, fragment);
        fragmentTransaction.commit();
}
}
//                ButterKnife.bind(this);
//
//                layoutManager = new LinearLayoutManager(this);
//                namesRecycleView.setLayoutManager(layoutManager);
//
//                recipesList = new ArrayList<>();
//                adapter = new RecipeAdapter(recipesList);
//
//
//
//
//
//
//
//
//
//
//
//
//        loadRecipeData();
//
//
//    }
//
//    private void loadRecipeData() {
//        getSupportLoaderManager().initLoader(0, null, this).forceLoad();
//    }
//
//    @Override
//    public Loader<ArrayList<Recipe>> onCreateLoader(int i, Bundle bundle) {
//
//
//        return new AsyncTaskLoader<ArrayList<Recipe>>(this) {
//
//            ArrayList<Recipe> parsedList;
//
//            @Override
//            public ArrayList<Recipe> loadInBackground() {
//
//                Log.d("MainActivity", "Load in background");
//                URL recipeUrl = NetworkUtils.buildURL();
//
//                Log.i("MainActivity", "recipeUrl" + recipeUrl);
//
//                try {
//                    String jsonResponse = NetworkUtils.getResponseFromHttpUrl(recipeUrl);
//                    parsedList = JsonUtils.parseJson(jsonResponse);
//                    Log.i("MainActivity", "Json list parsed");
//                    return parsedList;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return null;
//                }
//
//            }
//        };
//
//
//    }
//
//    @Override
//    public void onLoadFinished(Loader<ArrayList<Recipe>> loader, ArrayList<Recipe> recipes) {
//
//        //movieAdapter = new MovieAdapter(MainActivity.this, movies);
//        if (recipes != null && !recipes.equals("")) {
//            adapter = new RecipeAdapter(recipes);
//            namesRecycleView.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//           // gridView.setAdapter(movieAdapter);
//            //movieAdapter.notifyDataSetChanged();
//            //Log.i("Main Activity", "gridView set");
//            Log.i("MainActivity", "" + recipes);
//        } else {
//            Toast toast = Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_LONG);
//            toast.show();
//        }
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<ArrayList<Recipe>> loader) {
//
//    }
//}
