package com.example.roza.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.roza.bakingapp.models.Recipe;

import java.util.ArrayList;

/**
 * Created by hiddenpik on 12.05.2018.
 */

public class RecipeStepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        //getting click position from RecipeListFragment
        Intent mIntent = getIntent();
        int position = mIntent.getIntExtra("position", 0);



        //ArrayList<Recipe> recipes = mIntent.getParcelableArrayListExtra("arrayList");

        Recipe recipe = mIntent.getParcelableExtra("recipe");

        //sending Recipe object to StepsFragment
        Bundle bundle = new Bundle();
       // bundle.putInt("position", position);
        bundle.putParcelable("recipe", recipe);

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