package com.example.roza.bakingapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roza.bakingapp.Adapters.StepsAdapter;
import com.example.roza.bakingapp.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hiddenpik on 08.05.2018.
 */

public class StepsFragment extends Fragment {
    @BindView(R.id.ingredients_tv)
    TextView ingredientsTextView;
    @BindView(R.id.steps_rv)
    RecyclerView stepsRecycleView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Recipe.Steps> steps;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this, view);

       // int position = getArguments().getInt("position");
        final Recipe recipe = getArguments().getParcelable("recipe");
        Log.d("StepsFragment", ""   + (recipe != null ? recipe.getRecipeName() : null));

        steps = new ArrayList<>();
        steps =  recipe.getStepsList();

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
                fragmentTransaction.replace(R.id.fragment_steps_list, fragment).addToBackStack(null);
                fragmentTransaction.commit();


            }
        });


       layoutManager = new LinearLayoutManager(getActivity());
       stepsRecycleView.setLayoutManager(layoutManager);




       adapter = new StepsAdapter(steps);
       stepsRecycleView.setAdapter(adapter);
       adapter.notifyDataSetChanged();






        return view;
    }




}
