package com.example.jetpackcomposeapplication.mealzApp.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeapplication.mealzApp.model.MealsRepository
import com.example.jetpackcomposeapplication.mealzApp.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {
    /*fun getMeals(successCallback: (response: MealsCategoriesResponse?)-> Unit){
       repository.getMeals{ response ->
         successCallback(response)
      }
    }*/

    private val mealsJob = Job()

    init {
        // Custom Scope
        /*  val scope = CoroutineScope(mealsJob+Dispatchers.IO)
          scope.launch() {
              val meals = getMeals()
              mealsState.value = meals
          }*/
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())

    // Custom Scope
    /*  override fun onCleared() {
          super.onCleared()
          mealsJob.cancel()
      }*/
    private suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }
}
