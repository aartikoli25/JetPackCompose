package com.example.jetpackcomposeapplication.mealzApp.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposeapplication.mealzApp.ui.MealsCategoriesViewModel
import com.example.jetpackcomposeapplication.mealzApp.ui.theme.JetpackComposeApplicationTheme

class MealzActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeApplicationTheme {
                MealsCategoriesScreen()
            }
        }
    }
}

@Composable
fun MealsCategoriesScreen(modifier: Modifier = Modifier) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    Text(
        text = "Hello Compose!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeApplicationTheme {
        MealsCategoriesScreen()
    }
}
