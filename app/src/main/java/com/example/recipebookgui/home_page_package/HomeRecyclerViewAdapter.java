package com.example.recipebookgui.home_page_package;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipebookgui.R;
import com.example.recipebookgui.Recipe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {

    private Context context; // Can use this for a lot of methods for getting resources
    private List<Recipe> recipeList;
    private DatabaseReference databaseReference;

    public HomeRecyclerViewAdapter(Context context){
        this.context = context;
        this.recipeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recycler_view_holder, parent, false);
        return new HomeRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position) {

        Recipe recipe = recipeList.get(position); // Taking in each position of the viewholder, aka <item1>, <item2> (our recipes).
        holder.recipeName.setText(recipe.getRecipesName());
        String imageSource = recipe.getImageSource();

        //Here were checking if the Imagesource is Null since you can have No Image, Will set it to the Cookie Icon for recipes.
        if (imageSource!= null && !imageSource.isEmpty()) {
            byte[] imgArray = Base64.decode(imageSource, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(imgArray, 0, imgArray.length);
            holder.cardImage.setImageBitmap(decodedByte);
        }else{
            holder.cardImage.setImageResource(R.drawable.recipe_icon);
        }

        //Basic
        holder.favoriteHeart.setOnClickListener(v ->{
            if(!recipe.isFavorite()){
                holder.favoriteHeart.setImageResource(R.drawable.ic_favorite_filled);
                recipe.setFavorite(true);
            }else{
                holder.favoriteHeart.setImageResource(R.drawable.ic_favorite_outline);
                recipe.setFavorite(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
