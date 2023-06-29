package com.example.jetpackcomposeapplication.mealzApp.ui

import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeapplication.mealzApp.model.MealsRepository

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()) :
    ViewModel() {
}
