package com.example.roza.bakingapp.Adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roza.bakingapp.MainActivity;
import com.example.roza.bakingapp.R;
import android.app.Fragment;

  import com.example.roza.bakingapp.RecipeStepsActivity;
import com.example.roza.bakingapp.StepsFragment;
import com.example.roza.bakingapp.models.Recipe;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by hiddenpik on 07.05.2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private static ArrayList<Recipe> recipes;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);


        return new ViewHolder(view, new ViewHolder.ViewHolderClick() {
            public void onRecipeListItem(View view) {

                Log.d("RecipeAdapter", "onclick");
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.reciperNameTv.setTag(position);
        Recipe recipe = recipes.get(position);

        holder.reciperNameTv.setText(recipe.getRecipeName());

    }

    @Override
    public int getItemCount() {
        if (recipes != null && !recipes.isEmpty()) {
            return recipes.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView reciperNameTv;
        public ViewHolder.ViewHolderClick mListener;

        public ViewHolder(View itemView, ViewHolderClick viewHolderClick) {
            super(itemView);
            mListener = viewHolderClick;


            reciperNameTv = itemView.findViewById(R.id.recipe_name_textView);
            reciperNameTv.setOnClickListener(this);
        }

        // zrobic cos z tym contextem
        private Context context;

        @Override
        public void onClick(View view) {
            context = view.getContext();
            int position = (int) view.getTag();
            Toast.makeText(view.getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            mListener.onRecipeListItem(view);
            Intent intent = new Intent(itemView.getContext(), RecipeStepsActivity.class);
            //intent.putExtra("position", position);
            Recipe recipe = recipes.get(position);
            Bundle bundle = new Bundle();
            bundle.putParcelable("recipe", recipe);
            intent.putExtras(bundle);
            itemView.getContext().startActivity(intent);
//
//            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//            StepsFragment fragment = new StepsFragment();
//            fragmentTransaction.replace(R.id.fragment_name_list, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();


        }

        /*
        OnClick in RecyclerView from: https://stackoverflow.com/a/24933117/8131467
         */
        public static interface ViewHolderClick {
            public void onRecipeListItem(View view);
        }

    }

    public RecipeAdapter(List<Recipe> items) {
        recipes = (ArrayList<Recipe>) items;
    }
}







