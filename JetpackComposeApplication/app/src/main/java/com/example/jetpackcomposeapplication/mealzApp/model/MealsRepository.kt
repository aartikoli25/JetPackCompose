package com.example.jetpackcomposeapplication.mealzApp.model

import com.example.jetpackcomposeapplication.mealzApp.model.api.MealsWebService
import com.example.jetpackcomposeapplication.mealzApp.model.response.MealResponse
import com.example.jetpackcomposeapplication.mealzApp.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    // Using Retrofit
    /*    fun getMeals(successCallback: (response: MealsCategoriesResponse?)-> Unit){
        return webService.getMeals().enqueue(object : Callback<MealsCategoriesResponse> {
            override fun onResponse(
                call: Call<MealsCategoriesResponse>,
                response: Response<MealsCategoriesResponse>
            ) {
                if (response.isSuccessful)
                     successCallback(response.body())
            }

            override fun onFailure(call: Call<MealsCategoriesResponse>, t: Throwable) {
                //TODO treat failure
            }
        })
    }*/

    private var cachedMeals = listOf<MealResponse>()
    suspend fun getMeals(): MealsCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id: String): MealResponse? {
        return cachedMeals.firstOrNull() { it.id == id }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }
}
