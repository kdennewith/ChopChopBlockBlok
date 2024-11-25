package com.example.recipebookgui.home_page_package;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipebookgui.R;

public class HomePage extends Fragment {
    Toolbar topBar;
    TextView title;
    SearchView searchbar;
    RecyclerView recipeHolder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflating the xml home_page to this fragment.
        return inflater.inflate(R.layout.home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topBar = view.findViewById(R.id.title_bar_home);
        title = view.findViewById(R.id.toolbar_title);
        searchbar = view.findViewById(R.id.homepage_searchbar);
        recipeHolder = view.findViewById(R.id.homepage_recyclerview);

        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return true;
            }


            //What the searchbar will do when you type a new character.
            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        //This makes sure that the RecyclerView for the homepage is displayed Horizontally with
        // a LinearLayout.
        recipeHolder.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //Will implement when Firebase is added
    private void search(String query){

    }
    //Will implement when Firebase is added.
    private void filter(String currentText){

    }

}
