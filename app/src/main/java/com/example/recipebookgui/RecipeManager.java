package com.example.chopping_block;

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

    //add a recipe to Firebase
    public void addData(recipes recipe) {
        databaseReference.push().setValue(recipe).addOnSuccessListener(aVoid -> {
            //data successfully written to Firebase
            Toast.makeText(context.getApplicationContext(), "Recipe added successfully!", Toast.LENGTH_SHORT).show();}).addOnFailureListener(e -> {
            //handle write error
            Toast.makeText(context.getApplicationContext(), "Failed to add recipe: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }


    //save data to firebase
    public void saveRecipe(String recipeName, String description, List<Ingredient> ingredientsList, String recipeImage) {
        //create a new recipe object
        recipes newRecipe = new recipes(recipeName, description, ingredientsList, recipeImage);

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
                List<recipes> recipes = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    recipes recipe = snapshot.getValue(recipes.class);
                    if (recipe != null) {
                        recipes.add(recipe);
                    }
                }
                // recipes format of [recipe image from firebase] - Title from Firebase \n - description from firebase
                System.out.println("Recipes retrieved: " + recipes.size());
                for (recipes r : recipes) {
                    System.out.println("Title: " + r.getTitle() + ", Description: " + r.getDescription());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //  handle any errors that come
                System.err.println("Error reading data -  " + databaseError.getMessage());
            }
        });
    }
}