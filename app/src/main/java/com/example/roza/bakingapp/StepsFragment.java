package com.example.roza.bakingapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.roza.bakingapp.Adapters.StepsAdapter;
import com.example.roza.bakingapp.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepsFragment extends Fragment {
    @BindView(R.id.ingredients_tv)
    TextView ingredientsTextView;
    @BindView(R.id.steps_rv)
    RecyclerView stepsRecycleView;

    @BindView(R.id.button_previous_fragment_steps)
    Button previousButtonSteps;

    @BindView(R.id.button_next_fragment_steps)
    Button nextButtonSteps;


    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Recipe.Steps> steps;
    private ArrayList<Recipe> recipes;
    private boolean isTablet;
    private int position;
    private Recipe recipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this, view);

        steps = new ArrayList<>();

        recipes = getArguments().getParcelableArrayList("recipesList");
        position = getArguments().getInt("position");
        recipe = recipes.get(position);
        steps = recipe.getStepsList();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(recipe.getRecipeName());


        previousButtonSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0) {
                    position -= 1;
                    recipe = recipes.get(position);

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("recipesList", recipes);
                    bundle.putInt("position", position);


                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    StepsFragment fragment = new StepsFragment();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_steps_list, fragment);
                    fragmentTransaction.commit();
                }
            }
        });

        nextButtonSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((position < recipes.size() - 1)) {
                    position += 1;
                    recipe = recipes.get(position);

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("recipesList", recipes);
                    bundle.putInt("position", position);


                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    StepsFragment fragment = new StepsFragment();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_steps_list, fragment);
                    fragmentTransaction.commit();
                }

            }
        });


        if (view.findViewById(R.id.tablet_fragment_steps) != null) {
            isTablet = true;
        }

        ingredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Stes Fragment", "Click");

                Bundle bundle = new Bundle();
                bundle.putParcelable("recipe", recipe);


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                IngredientsListFragment fragment = new IngredientsListFragment();
                fragment.setArguments(bundle);

                if (isTablet) {

                    fragmentTransaction.replace(R.id.recipe_detail, fragment).addToBackStack(null);
                    fragmentTransaction.commit();
                    Log.d("StepsFragment", "wywolane");


                } else {


                    fragmentTransaction.replace(R.id.fragment_steps_list, fragment).addToBackStack(null);
                    fragmentTransaction.commit();
                }


            }
        });


        layoutManager = new LinearLayoutManager(getActivity());
        stepsRecycleView.setLayoutManager(layoutManager);


        adapter = new StepsAdapter(getContext(), steps);
        stepsRecycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }


}
