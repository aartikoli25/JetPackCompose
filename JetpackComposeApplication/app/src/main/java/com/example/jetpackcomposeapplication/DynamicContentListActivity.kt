package com.example.jetpackcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class DynamicContentListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicContentListScreen()
        }
    }
}

@Composable
fun DynamicContentListScreen() {
    val greetingListState =
        remember { mutableStateListOf<String>("Jack", "Wendy", "Janice", "Eben") }
    val newNameStateContent = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(greetingListState, { greetingListState.add(newNameStateContent.value) },
            newNameStateContent.value,
            { newName -> newNameStateContent.value = newName })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingList(
    nameList: List<String>,
    buttonClick: () -> Unit,
    textFieldValue: String,
    textFieldUpdate: (newName: String) -> Unit
) {
    for (name in nameList) {
        GreetingName(name = name)
    }

    TextField(value = textFieldValue, onValueChange = textFieldUpdate)
    Button(onClick = buttonClick) {
        Text(text = "Add new member")
    }
}

@Composable
fun GreetingName(name: String) {
    Text(
        text = "Hi $name", style = MaterialTheme.typography.headlineMedium
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDynamicContentListActivity() {
    DynamicContentListScreen()
}
