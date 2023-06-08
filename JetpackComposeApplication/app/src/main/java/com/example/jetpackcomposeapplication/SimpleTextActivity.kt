package com.example.jetpackcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class SimpleTextActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingText("User")
        }
    }
}

@Composable
fun GreetingText(name: String) {
    Text(
        text = "Hi $name!", modifier = Modifier
            .size(width = 100.dp, height = 100.dp)
            .clickable(onClick = {})
            .padding(all = 12.dp),
        /*style = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold,
            fontSize = 18.sp)*/
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun GreetingButton() {
    Button(onClick = { }) {
        GreetingText(name = "Click here")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSimpleTextActivity() {
    GreetingText("User")
}