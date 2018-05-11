package com.example.roza.bakingapp.utils;

import android.util.Log;

import com.example.roza.bakingapp.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        ArrayList<Recipe> ingredientsList = new ArrayList<>();

        for (int i = 0; i < resultsArray.length(); i++) {

            //zapisać wszystko w zmiennych jednak i potem poustawiać do

            Recipe recipe = new Recipe();

            JSONObject recipeObject = resultsArray.getJSONObject(i);


            recipe.setRecipeId(recipeObject.getInt(ID));
            recipe.setRecipeName(recipeObject.getString(NAME));
            JSONArray ingredientsArray = recipeObject.getJSONArray(INGREDIENTS);

            for (int j = 0; j < ingredientsArray.length(); j++ ) {

                JSONObject ingredientObject = ingredientsArray.getJSONObject(j);

                recipe.setIngredientQuantity(ingredientObject.getInt(QUANTITY));
                recipe.setIgredientMeasure(ingredientObject.getString(MEASURE));
                recipe.setIngredient(ingredientObject.getString(INGREDIENT));



                Log.d("Json", "" + recipe.getIngredient());


            }


            JSONArray stepsArray = recipeObject.getJSONArray(STEPS);

            for (int k = 0; k < stepsArray.length(); k++) {

                JSONObject stepObject = stepsArray.getJSONObject(k);

                recipe.setStepId(stepObject.getInt(ID));
                recipe.setStepShortDescription(stepObject.getString(SHORT_DESCRIPTION));
                recipe.setStepDescription(stepObject.getString(DESCRIPTION));
                recipe.setStepVideoUrl(stepObject.getString(VIDEO_URL));
                recipe.setStepThumbnailUrl(stepObject.getString(THUMBNAIL_URL));


            }

            recipe.setRecipeServings(recipeObject.getInt(SERVINGS));
            recipe.setRecipeImage(recipeObject.getString(IMAGE));

            recipes.add(recipe);
            Log.d("Json", "" + recipe.getRecipeName());
            Log.d("Json", "" + recipe.getStepVideoUrl());
            Log.d("Json", "" + recipe.getIngredient());
            Log.d("json","" + recipes);

        }

        return recipes;
    }

}
