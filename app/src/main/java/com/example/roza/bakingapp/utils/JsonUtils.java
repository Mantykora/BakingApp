package com.example.roza.bakingapp.utils;

import android.util.Log;

import com.example.roza.bakingapp.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiddenpik on 05.05.2018.
 */

public class JsonUtils {

    public static ArrayList<Recipe> parseJson(String json) throws JSONException {

        final String ID = "id";
        final String NAME = "name";
        final String INGREDIENTS = "ingredients";
        final String STEPS = "steps";
        final String SERVINGS = "servings";
        final String IMAGE = "image";

        final String QUANTITY = "quantity";
        final String MEASURE = "measure";
        final String INGREDIENT = "ingredient";

        final String SHORT_DESCRIPTION = "shortDescription";
        final String DESCRIPTION = "description";
        final String VIDEO_URL = "videoURL";
        final String THUMBNAIL_URL = "thumbnailURL";

        JSONArray resultsArray = new JSONArray(json);

        final ArrayList<Recipe> recipes = new ArrayList<>();


        for (int i = 0; i < resultsArray.length(); i++) {

            //zapisać wszystko w zmiennych jednak i potem poustawiać do

            Recipe recipe = new Recipe();

            JSONObject recipeObject = resultsArray.getJSONObject(i);


            recipe.setRecipeId(recipeObject.getInt(ID));
            recipe.setRecipeName(recipeObject.getString(NAME));
            JSONArray ingredientsArray = recipeObject.getJSONArray(INGREDIENTS);


            ArrayList<Recipe.Ingredients> ingredientsArrayList = new ArrayList<>();
            Recipe.Ingredients ingredients = new Recipe.Ingredients();

            for (int j = 0; j < ingredientsArray.length(); j++ ) {

                JSONObject ingredientObject = ingredientsArray.getJSONObject(j);

                ingredients.setIngredientQuantity(ingredientObject.getInt(QUANTITY));
                ingredients.setIgredientMeasure(ingredientObject.getString(MEASURE));
                ingredients.setIngredient(ingredientObject.getString(INGREDIENT));

                ingredientsArrayList.add(ingredients);



                Log.d("ingredients", ingredientsArrayList.toString());


            }
            recipe.setIngredientsList(ingredientsArrayList);
            Log.d("ingredients", ingredientsArrayList.toString());


            ArrayList<Recipe.Steps> stepsArrayList = new ArrayList<>();
            Recipe.Steps steps = new Recipe.Steps();
            JSONArray stepsArray = recipeObject.getJSONArray(STEPS);

            for (int k = 0; k < stepsArray.length(); k++) {

                JSONObject stepObject = stepsArray.getJSONObject(k);

                steps.setStepId(stepObject.getInt(ID));
                steps.setStepShortDescription(stepObject.getString(SHORT_DESCRIPTION));
                steps.setStepDescription(stepObject.getString(DESCRIPTION));
                steps.setStepVideoUrl(stepObject.getString(VIDEO_URL));
                steps.setStepThumbnailUrl(stepObject.getString(THUMBNAIL_URL));

                stepsArrayList.add(steps);


            }
            recipe.setStepsList(stepsArrayList);

            recipe.setRecipeServings(recipeObject.getInt(SERVINGS));
            recipe.setRecipeImage(recipeObject.getString(IMAGE));

            recipes.add(recipe);
            Log.d("Json", "" + recipe.getRecipeName());
//            Log.d("Json", "" + recipe.getStepVideoUrl());
//            Log.d("Json", "" + recipe.getIngredient());
            Log.d("json","" + recipes);

        }

        return recipes;
    }

}
