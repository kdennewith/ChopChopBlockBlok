package com.example.recipebookgui;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.Toast;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class RecipeManager {

    private DatabaseReference databaseReference;
    private Context context;

    public RecipeManager(Context context) {
        this.context = context;
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
    }


    //save data to firebase
    public void saveRecipe(List<Ingredient> ingredientsList, String recipesName, String imageSource, String description, boolean favorite) {
        //create a new recipe object
        Recipe newRecipe = new Recipe(ingredientsList, recipesName, imageSource, description, favorite);

        //push recipe data to Firebase
        DatabaseReference recipeRef = FirebaseDatabase.getInstance().getReference("recipes");
        recipeRef.push().setValue(newRecipe).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //show success toast
                Toast.makeText(context, "Recipe added successfully! :)", Toast.LENGTH_SHORT).show();
            } else {
                //show failure toast
                Toast.makeText(context, "Failed to add recipe: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    //read recipes from Firebase
    public void readData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Recipe> recipes = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Recipe recipe = snapshot.getValue(Recipe.class);
                    if ( recipe != null) {
                        recipes.add(recipe);
                    }
                }
                //  recipes format of [recipe image from firebase] - Title from Firebase \n - description from firebase
                System.out.println("Recipes retrieved: " + recipes.size());
                for (Recipe r : recipes) {
                    //just print info - next update to reflect xml
                    System.out.println("Title: " + r.getRecipesName() + ", Description: " + r.getDescription());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //  handle any errors that come by showing message
                System.err.println("Error reading data -  " + databaseError.getMessage());
            }
        });
    }
}