package com.example.roza.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roza.bakingapp.models.Recipe;

/**
 * Created by hiddenpik on 15.05.2018.
 */

public class RecipeDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        final Recipe.Steps step = getArguments().getParcelable("step");

        //Log.d("RecipeDetailFragment", step.getStepDescription());

        return view;
    }
}
