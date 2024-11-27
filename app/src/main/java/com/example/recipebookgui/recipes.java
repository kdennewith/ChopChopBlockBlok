package com.example.recipebookgui;

import java.util.List;

//can be the MAIN-PAGE just add search bar and favorites
public class recipes {
    //3 items per database recipe
    private String photoUrl;
    private String description;
    private String title;
    private List<Ingredient> ingredients; // A list of ingredients

    //empty constructor required for Firebase Realtime Database
    public recipes() {}
    //same as Recipe but no Favorites functionality - can merge to just Recipe
    public recipes(String title, String description, List<Ingredient> ingredients, String photoUrl) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.photoUrl = photoUrl;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
