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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roza.bakingapp.MainActivity;
import com.example.roza.bakingapp.R;

import android.app.Fragment;

import com.example.roza.bakingapp.RecipeStepsActivity;
import com.example.roza.bakingapp.StepsFragment;
import com.example.roza.bakingapp.models.Recipe;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;


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


        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();


            mListener.onRecipeListItem(view);
            Intent intent = new Intent(itemView.getContext(), RecipeStepsActivity.class);
            Recipe recipe = recipes.get(position);
            Bundle bundle = new Bundle();
            bundle.putParcelable("recipe", recipe);
            bundle.putInt("position", position);
            bundle.putParcelableArrayList("recipesList", recipes);
            intent.putExtras(bundle);
            itemView.getContext().startActivity(intent);


        }

        /*
        OnClick in RecyclerView from: https://stackoverflow.com/a/24933117/8131467
         */
        public interface ViewHolderClick {
            void onRecipeListItem(View view);
        }

    }

    public RecipeAdapter(List<Recipe> items) {
        recipes = (ArrayList<Recipe>) items;
    }
}







