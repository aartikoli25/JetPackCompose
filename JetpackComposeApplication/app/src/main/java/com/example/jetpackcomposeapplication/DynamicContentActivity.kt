package com.example.jetpackcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class DynamicContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicScreen()
        }
    }
}

@Composable
fun DynamicScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList()
    }
}

@Composable
fun GreetingList() {
    val greetingListState =
        remember { mutableStateListOf<String>("Jack", "Wendy", "Janice", "Eben") }
    for (name in greetingListState) {
        Greeting(name = name)
    }
    Button(onClick = { greetingListState.add("New name..") }) {
        Text(text = "Add new member")
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hi $name",
        style = MaterialTheme.typography.headlineMedium
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDynamicContentActivity() {
    DynamicScreen()
}