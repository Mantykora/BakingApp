package com.example.roza.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiddenpik on 05.05.2018.
 */

public class Recipe implements Parcelable {

    private int recipeId;
    private String recipeName;
    private int recipeServings;
    private String recipeImage;

    private ArrayList<Ingredients> ingredientsList;


    private ArrayList<Steps> stepsList;


    public Recipe() {

    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getRecipeServings() {
        return recipeServings;
    }

    public void setRecipeServings(int recipeServings) {
        this.recipeServings = recipeServings;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public ArrayList<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(ArrayList<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public ArrayList<Steps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(ArrayList<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    public static class Ingredients implements Parcelable {
        private int ingredientQuantity;
        private String igredientMeasure;
        private String ingredient;

        public int getIngredientQuantity() {
            return ingredientQuantity;
        }

        public void setIngredientQuantity(int ingredientQuantity) {
            this.ingredientQuantity = ingredientQuantity;
        }

        public String getIgredientMeasure() {
            return igredientMeasure;
        }

        public void setIgredientMeasure(String igredientMeasure) {
            this.igredientMeasure = igredientMeasure;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.ingredientQuantity);
            dest.writeString(this.igredientMeasure);
            dest.writeString(this.ingredient);
        }

        public Ingredients() {
        }

        protected Ingredients(Parcel in) {
            this.ingredientQuantity = in.readInt();
            this.igredientMeasure = in.readString();
            this.ingredient = in.readString();
        }

        public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
            @Override
            public Ingredients createFromParcel(Parcel source) {
                return new Ingredients(source);
            }

            @Override
            public Ingredients[] newArray(int size) {
                return new Ingredients[size];
            }
        };
    }

    public static class Steps implements Parcelable {
        private int stepId;
        private String stepShortDescription;
        private String stepDescription;
        private String stepVideoUrl;
        private String stepThumbnailUrl;

        public int getStepId() {
            return stepId;
        }

        public void setStepId(int stepId) {
            this.stepId = stepId;
        }

        public String getStepShortDescription() {
            return stepShortDescription;
        }

        public void setStepShortDescription(String stepShortDescription) {
            this.stepShortDescription = stepShortDescription;
        }

        public String getStepDescription() {
            return stepDescription;
        }

        public void setStepDescription(String stepDescription) {
            this.stepDescription = stepDescription;
        }

        public String getStepVideoUrl() {
            return stepVideoUrl;
        }

        public void setStepVideoUrl(String stepVideoUrl) {
            this.stepVideoUrl = stepVideoUrl;
        }

        public String getStepThumbnailUrl() {
            return stepThumbnailUrl;
        }

        public void setStepThumbnailUrl(String stepThumbnailUrl) {
            this.stepThumbnailUrl = stepThumbnailUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.stepId);
            dest.writeString(this.stepShortDescription);
            dest.writeString(this.stepDescription);
            dest.writeString(this.stepVideoUrl);
            dest.writeString(this.stepThumbnailUrl);
        }

        public Steps() {
        }

        protected Steps(Parcel in) {
            this.stepId = in.readInt();
            this.stepShortDescription = in.readString();
            this.stepDescription = in.readString();
            this.stepVideoUrl = in.readString();
            this.stepThumbnailUrl = in.readString();
        }

        public static final Creator<Steps> CREATOR = new Creator<Steps>() {
            @Override
            public Steps createFromParcel(Parcel source) {
                return new Steps(source);
            }

            @Override
            public Steps[] newArray(int size) {
                return new Steps[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.recipeId);
        dest.writeString(this.recipeName);
        dest.writeInt(this.recipeServings);
        dest.writeString(this.recipeImage);
        dest.writeList(this.ingredientsList);
        dest.writeList(this.stepsList);
    }

    protected Recipe(Parcel in) {
        this.recipeId = in.readInt();
        this.recipeName = in.readString();
        this.recipeServings = in.readInt();
        this.recipeImage = in.readString();
        this.ingredientsList = new ArrayList<Ingredients>();
        in.readList(this.ingredientsList, Ingredients.class.getClassLoader());
        this.stepsList = new ArrayList<Steps>();
        in.readList(this.stepsList, Steps.class.getClassLoader());
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}

