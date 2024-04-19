package com.example.jetpackcomposeapplication.mealzApp.model.api

import com.example.jetpackcomposeapplication.mealzApp.model.response.MealsCategoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MealsWebService {
    private var api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    //Using Retrofit
    //fun getMeals() : Call<MealsCategoriesResponse>{
    // return api.getMeals()
    //}
    suspend fun getMeals(): MealsCategoriesResponse {
        return api.getMeals()
    }

    interface MealsApi {
        @GET("categories.php")
//        fun getMeals(): Call<MealsCategoriesResponse>
        suspend fun getMeals(): MealsCategoriesResponse

    }
}