package com.example.roza.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roza.bakingapp.R;
import com.example.roza.bakingapp.RecipeDetailActivity;
import com.example.roza.bakingapp.RecipeDetailFragment;
import com.example.roza.bakingapp.RecipeStepsActivity;
import com.example.roza.bakingapp.models.Recipe;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiddenpik on 13.05.2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private static ArrayList<Recipe.Steps> steps;
    private static boolean isTablet;
    private static Context context;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_list_item, parent, false);


       if (view.findViewById(R.id.tablet_steps_list_item) != null) {

           isTablet = true;
       }

       return new ViewHolder(view, new ViewHolder.ViewHolderClick() {
           public void onStepListItem(View view) {

               Log.d("StepeAdapter", "onclick");
           }
       });

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       holder.stepsTv.setTag(position);
        Recipe.Steps step = steps.get(position);
        int numberOfStep = position + 1;
        holder.stepsTv.setText(step.getStepId() + ". " + step.getStepShortDescription());

   }

    @Override
   public int getItemCount() {
       if (steps != null && !steps.isEmpty()) {
           return steps.size();
       } else {
       return 0;
   }
   }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       public final TextView stepsTv;
        public ViewHolder.ViewHolderClick mListener;

        public ViewHolder(View itemView, ViewHolderClick viewHolderClick) {
            super(itemView);
            mListener = viewHolderClick;

            stepsTv = itemView.findViewById(R.id.steps_tv);

            stepsTv.setOnClickListener(this);


       }

       @Override
       public void onClick(View view) {




           int position = (int) view.getTag();
           mListener.onStepListItem(view);

           Intent intent = new Intent(itemView.getContext(), RecipeDetailActivity.class);

           Recipe.Steps step = steps.get(position);
           Bundle bundle = new Bundle();
           bundle.putParcelable("step", step);
           bundle.putInt("position", position);
           bundle.putParcelableArrayList("stepsList", steps);


           if (isTablet) {

               FragmentManager fragmentManager = ((RecipeStepsActivity)context).getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               RecipeDetailFragment fragment = new RecipeDetailFragment();
               fragment.setArguments(bundle);


               fragmentTransaction.replace(R.id.recipe_detail, fragment);
               fragmentTransaction.commit();

           } else {



               intent.putExtras(bundle);

               itemView.getContext().startActivity(intent);



           }



       }

        public static interface ViewHolderClick {
            public void onStepListItem(View view);
        }

    }
    public StepsAdapter(Context context, ArrayList<Recipe.Steps> items) {
        steps = items;
        this.context = context;
    }
}
