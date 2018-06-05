package com.example.roza.bakingapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.roza.bakingapp.Adapters.IngredientsAdapter;
import com.example.roza.bakingapp.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsListFragment extends Fragment {

    @BindView(R.id.ingredients_rv)
    RecyclerView ingredientsRecycleView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Recipe.Ingredients> ingredients;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        ButterKnife.bind(this, view);

        final Recipe recipe = getArguments().getParcelable("recipe");

        ingredients = new ArrayList<>();
        ingredients = recipe != null ? recipe.getIngredientsList() : null;

        layoutManager = new LinearLayoutManager(getActivity());
        ingredientsRecycleView.setLayoutManager(layoutManager);


        adapter = new IngredientsAdapter(ingredients);
        ingredientsRecycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }
}
