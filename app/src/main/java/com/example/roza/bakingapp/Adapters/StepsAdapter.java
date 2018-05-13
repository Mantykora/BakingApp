package com.example.roza.bakingapp.Adapters;

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

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private ArrayList<Recipe.Steps> steps;

   @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_list_item, parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       holder.stepsTv.setTag(position);
        Recipe.Steps step = steps.get(position);
        int numberOfStep = position + 1;
        holder.stepsTv.setText("Step " + numberOfStep);

   }

    @Override
   public int getItemCount() {
       if (steps != null && !steps.isEmpty()) {
           return steps.size();
       } else {
       return 0;
   }
   }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       public final TextView stepsTv;

        public ViewHolder(View itemView) {
            super(itemView);

            stepsTv = itemView.findViewById(R.id.steps_tv);
       }
    }
    public StepsAdapter(ArrayList<Recipe.Steps> items) {
        steps = items;
    }
}
