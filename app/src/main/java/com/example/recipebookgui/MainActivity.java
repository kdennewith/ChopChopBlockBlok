package com.example.recipebookgui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set default fragment, in this case our HomePage.
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.view_container, new HomePage())
                    .commit();
        }

        /*
         * This is the bottom_navigation_menu.xml implementation, Basically each button
         *  is associated with creating a new Fragment object. Example: item, which is
         *  the <Item></Item> 's on the bottom_nav_menu.xml have IDs corresponding to the
         *  R.id.fragmentname (R.id.home_page), which then sets the Fragment as a HomePage.
         *  Setting the Fragment to null at the start should make it so Fragments won't build
         *  up.
         */
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.home_page) {
                selectedFragment = new HomePage();
            } else if (item.getItemId() == R.id.recipe_page) {
                selectedFragment = new RecipePage();
            } else if (item.getItemId() == R.id.new_recipe_page) {
                selectedFragment = new NewRecipePage();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_container, selectedFragment)
                        .commit();
            }

            return true;
        });
    }
}