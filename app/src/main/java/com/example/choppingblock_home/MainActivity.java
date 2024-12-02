package com.example.choppingblock_home;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.choppingblock_home.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //api call to add ingredient
        Ingredient newIngredient = new Ingredient("Tomato", 2, "pieces", "Pasta");
        Retrofit retrofit = RetrofitClient.getInstance();
        MyApiService apiService = retrofit.create(MyApiService.class);

        //how to request to add ingredient
        Call<Void> addCall = apiService.addIngredient(newIngredient);
        addCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API", "Ingredient added successfully");
                } else {
                    Log.e("API", "Failed to add ingredient");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API", "Error: " + t.getMessage());
            }
        });

        //How to fetch and read and stuff from mysql
        Call<List<Ingredient>> getCall = apiService.getIngredients("Pasta");
        getCall.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if (response.isSuccessful()) {
                    List<Ingredient> ingredients = response.body();
                    // Handle the fetched ingredients
                    Log.d("API", "Fetched ingredients: " + ingredients.size());
                } else {
                    Log.e("API", "Failed to fetch ingredients");
                }
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                Log.e("API", "Error: " + t.getMessage());
            }
        });
    }
}