package com.example.roza.bakingapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.roza.bakingapp.R;
import com.example.roza.bakingapp.models.Recipe;

import java.util.ArrayList;
import java.util.List;


public class ConfigureAdapter extends ArrayAdapter<Recipe> {

    private Context context;

    private static ArrayList<Recipe> recipes;


    public ConfigureAdapter(@NonNull Context context, ArrayList<Recipe> items) {
        super(context, 0);
        this.context = context;
        recipes = items;
    }


    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup viewGroup) {


        if (view == null) {


            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


            view = mInflater.inflate(R.layout.configure_list_item, viewGroup, false);
            TextView textView = view.findViewById(R.id.configure_tv_item);

            Recipe recipe = recipes.get(position);

            Log.d("ConfigureAdapter", "" + recipe);

            textView.setText(recipe.getRecipeName());

        }

        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
