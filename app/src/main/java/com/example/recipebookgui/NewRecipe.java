package com.example.recipebookgui;

import android.content.Context;
import android.widget.Toast;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class NewRecipe extends AppCompatActivity {

    private EditText recipeNameEditText;
    private EditText descriptionEditText;
    private LinearLayout ingredientsLayout;
    private RecipeManager recipeManager; // to manage recipes and Firebase

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newrecipe_page);

        /*
        recipeNameEditText = findViewById(R.id.recipe_name);
        descriptionEditText = findViewById(R.id.recipe_description);
        ingredientsLayout = findViewById(R.id.ingredients_layout);
    */
        //initialize the RecipeManager to manage recipe saving
        recipeManager = new RecipeManager(this);

        //add initial ingredient fields dynamically
        addIngredientFields();
    }

    // add ingredient input fields
    private void addIngredientFields() {
        //adding 2 EditText fields for ingredient name and units
        EditText ingredientName = new EditText(this);
        ingredientName.setHint("Ingredient Name");
        ingredientsLayout.addView(ingredientName);

        EditText ingredientUnits = new EditText(this);
        ingredientUnits.setHint("Units");
        ingredientsLayout.addView(ingredientUnits);
    }

    //save a new recipe
    private void saveRecipe() {
        String recipeName = recipeNameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        //validate that recipe name and description are not empty
        if (recipeName.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill in the recipe name and description.", Toast.LENGTH_SHORT).show();
            return;
        }

        //collect ingredient data from the user input
        List<Ingredient> ingredientsList = new ArrayList<>();
        int childCount = ingredientsLayout.getChildCount();
        for (int i = 0; i < childCount; i += 2) { // assuming name is even, units is odd
            EditText ingredientName = (EditText) ingredientsLayout.getChildAt(i);
            EditText ingredientUnits = (EditText) ingredientsLayout.getChildAt(i + 1);

            String name = ingredientName.getText().toString();
            String units = ingredientUnits.getText().toString();

            //check if both fields are filled
            if (!name.isEmpty() && !units.isEmpty()) {
                //parse the amount from the string to integer and create an Ingredient object
                int amount = Integer.parseInt(units); // assuming unit is entered as integer for now
                ingredientsList.add(new Ingredient(name, amount, "unit")); //replace "unit" with actual unit if needed
            } else {
                // show message if any ingredient field is empty - which shouldnt happen
                Toast.makeText(this, "Please fill in all ingredient fields.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //replaced with a user-uploaded image later
        String recipeImage = "img1"; //image picker here**

        //save the recipe to Firebase using RecipeManager
        recipeManager.saveRecipe(ingredientsList, description, recipeName, recipeImage, false);

    }
}