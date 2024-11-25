package com.example.recipebookgui;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String recipesName;
    private String imageSource;
    private List<Ingredient> ingredientList;
    private String description;
    private boolean favorite;

    public Recipe(List<Ingredient> ingredientList, String recipesName, String imageSource, String description, boolean favorite) {
        this.recipesName = recipesName;
        this.imageSource = imageSource;
        this.ingredientList = ingredientList;
        this.description = description;
        this.favorite = favorite;
    }


    public String getRecipesName() {
        return recipesName;
    }

    public void setRecipesName(String recipesName) {
        this.recipesName = recipesName;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo(){
        return"";
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}