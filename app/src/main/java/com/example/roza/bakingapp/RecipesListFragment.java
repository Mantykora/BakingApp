package com.example.roza.bakingapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by hiddenpik on 09.05.2018.
 */

public class RecipesListFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Recipe>> {

        @BindView(R.id.recipe_names_rv)
        RecyclerView namesRecycleView;

        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.Adapter adapter;
        private List<Recipe> recipesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        ButterKnife.bind(this, view);






      if (view.findViewById(R.id.tablet_activity_main) != null) {

          layoutManager = new GridLayoutManager(getActivity(), 5);


      } else {
          layoutManager = new LinearLayoutManager(getActivity());
          namesRecycleView.setLayoutManager(layoutManager);

      }
        recipesList = new ArrayList<>();
        adapter = new RecipeAdapter(recipesList);












        loadRecipeData();
        return view;
    }

    private void loadRecipeData() {
        getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Override
    public Loader<ArrayList<Recipe>> onCreateLoader(int i, Bundle bundle) {


        return new AsyncTaskLoader<ArrayList<Recipe>>(getActivity()) {

            ArrayList<Recipe> parsedList;

            @Override
            public ArrayList<Recipe> loadInBackground() {

                Log.d("MainActivity", "Load in background");
                URL recipeUrl = NetworkUtils.buildURL();

                Log.i("MainActivity", "recipeUrl" + recipeUrl);

                try {
                    String jsonResponse = NetworkUtils.getResponseFromHttpUrl(recipeUrl);
                    parsedList = JsonUtils.parseJson(jsonResponse);
                    Log.i("MainActivity", "Json list parsed");
                    return parsedList;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

            }
        };


    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Recipe>> loader, ArrayList<Recipe> recipes) {
        //movieAdapter = new MovieAdapter(MainActivity.this, movies);
        if (recipes != null && !recipes.equals("")) {
            adapter = new RecipeAdapter(recipes);
            namesRecycleView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            // gridView.setAdapter(movieAdapter);
            //movieAdapter.notifyDataSetChanged();
            //Log.i("Main Activity", "gridView set");
            Log.i("MainActivity", "" + recipes);
        } else {
            Toast toast = Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Recipe>> loader) {

    }

//    @Override
//    public void onLoadFinished(Loader<ArrayList<Recipe>> loader, ArrayList<Recipe> recipes) {
//
//        //movieAdapter = new MovieAdapter(MainActivity.this, movies);
//        if (recipes != null && !recipes.equals("")) {
//            adapter = new RecipeAdapter(recipes);
//            namesRecycleView.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//            // gridView.setAdapter(movieAdapter);
//            //movieAdapter.notifyDataSetChanged();
//            //Log.i("Main Activity", "gridView set");
//            Log.i("MainActivity", "" + recipes);
//        } else {
//            Toast toast = Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_LONG);
//            toast.show();
//        }
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<ArrayList<Recipe>> loader) {
//
//    }
}
