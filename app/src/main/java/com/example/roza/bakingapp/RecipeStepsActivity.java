package com.example.roza.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.roza.bakingapp.models.Recipe;

import java.util.ArrayList;


public class RecipeStepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        if (findViewById(R.id.tablet_fragment_recipes) != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragmentTransaction.add(R.id.recipe_detail, fragment);
            fragmentTransaction.commit();
        }


        Intent mIntent = getIntent();
        int position = mIntent.getIntExtra("position", 0);
        ArrayList<Recipe> recipes = getIntent().getParcelableArrayListExtra("recipesList");


        Recipe recipe = mIntent.getParcelableExtra("recipe");

        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe", recipe);
        bundle.putInt("position", position);
        bundle.putParcelableArrayList("recipesList", recipes);

        Log.d("RecipeStepsActivity", "" + recipe.getRecipeName());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StepsFragment fragment = new StepsFragment();
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_steps_list, fragment);
        fragmentTransaction.commit();


        Log.d("position", "" + position);
    }

}