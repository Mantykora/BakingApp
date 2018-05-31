package com.example.roza.bakingapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.roza.bakingapp.models.Recipe;

/**
 * Created by hiddenpik on 15.05.2018.
 */

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Recipe.Steps step = getIntent().getParcelableExtra("step");

        Bundle bundle = new Bundle();

        bundle.putParcelable("step", step);

        Log.d("RecipeDetailActivity", "" + step.getStepDescription());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.recipe_detail, fragment);
        fragmentTransaction.commit();

    }
}
