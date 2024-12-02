package com.example.choppingblock_home;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.List;

public interface MyApiService {
    @POST("addIngredient")
    Call<Void> addIngredient(@Body Ingredient ingredient);

    @GET("getIngredients")
    Call<List<Ingredient>> getIngredients(@Query("recipesName") String recipesName);
}