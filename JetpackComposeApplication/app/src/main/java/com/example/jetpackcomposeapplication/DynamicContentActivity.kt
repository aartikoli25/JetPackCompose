package com.example.jetpackcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


var nameList: List<String> = listOf("Jack","Wendy", "Janice", "Saurabh", "Eben")
class DynamicContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingList(names = nameList)
        }
    }
}
@Composable
fun GreetingList(names: List<String>){
    Column {
        for (name in names){
            Greeting(name = name)
        }
    }
}
@Composable
fun Greeting(name: String) {
    Text(
        text = "Hi $name"
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDynamicContentActivity() {
    GreetingList(names = nameList)
}