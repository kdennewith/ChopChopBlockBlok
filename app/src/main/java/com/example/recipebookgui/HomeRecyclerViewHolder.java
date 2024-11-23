package com.example.recipebookgui;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This is the holder class that binds all the elements of home_recycler_view_holder.xml
 *  to the home_page.xml's recyclerView.
 */
public class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView cardImage;
    TextView recipeName;
    ImageButton favoriteHeart;

    public HomeRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.home_cardView);
        cardImage = itemView.findViewById(R.id.card_image);
        recipeName = itemView.findViewById(R.id.card_recipe_name);
        favoriteHeart = itemView.findViewById(R.id.favorite_heart);
    }


}
