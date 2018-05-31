package com.example.roza.bakingapp.Adapters;

/**
 * Created by hiddenpik on 13.05.2018.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roza.bakingapp.R;
import com.example.roza.bakingapp.models.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiddenpik on 13.05.2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private ArrayList<Recipe.Ingredients> ingredients;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.stepsTv.setTag(position);
//        Recipe.Steps step = steps.get(position);
//        int numberOfStep = position + 1;
//        holder.stepsTv.setText("Step " + numberOfStep);
//
//        holder.reciperNameTv.setTag(position);
//        Recipe recipe = recipes.get(position);

       // holder.reciperNameTv.setText(recipe.getRecipeName());


        holder.quantityTv.setTag(position);
        holder.measureTv.setTag(position);
        holder.ingredientTv.setTag(position);


        Recipe.Ingredients ingredient = ingredients.get(position);

        holder.quantityTv.setText(String.valueOf(ingredient.getIngredientQuantity()));
        holder.measureTv.setText(String.valueOf(ingredient.getIgredientMeasure()));
        holder.ingredientTv.setText(String.valueOf(ingredient.getIngredient()));

    }

    @Override
    public int getItemCount() {
        if (ingredients != null && !ingredients.isEmpty()) {
            return ingredients.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //public final TextView stepsTv;
        public final TextView quantityTv;
        public final TextView measureTv;
        public final TextView ingredientTv;

        public ViewHolder(View itemView) {
            super(itemView);

            quantityTv = itemView.findViewById(R.id.quantity_tv);
            measureTv = itemView.findViewById(R.id.measure_tv);
            ingredientTv = itemView.findViewById(R.id.ingrediet_tv);
            //stepsTv = itemView.findViewById(R.id.steps_tv);
        }
    }
    public IngredientsAdapter (ArrayList<Recipe.Ingredients> items) {
        ingredients = items;
    }
}

