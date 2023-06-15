package com.example.jetpackcomposeapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapplication.ui.theme.MyTheme
import com.example.jetpackcomposeapplication.ui.theme.lightGreen

class ProfileCardLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ProfileCardScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileCardScreen(userProfiles: List<UserProfile> = userProfileList) {
    Scaffold(topBar = { AppBar() }) { innerPadding ->
        Surface(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
        ) {
            Column {
                for (userProfile in userProfiles) {
                    ProfileCard(userProfile = userProfile)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
            navigationIcon = {
                Icon(Icons.Filled.Home, "Home Icon", Modifier.padding(horizontal = 12.dp))
            },
            title = { Text(text = "My Contacts") },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan)
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile) {
    Card(
            modifier = Modifier
                    .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.Top)
                    .shadow(elevation = 8.dp),
            colors = CardDefaults.cardColors(
                    containerColor = Color.White
            )
    ) {
        Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.drawableId, userProfile.status)
            ProfileContent(userProfile.name, userProfile.status)
        }
    }
}

@Composable
fun ProfilePicture(drawableId: Int, onlineStatus: Boolean) {
    Card(
            shape = CircleShape,
            border = BorderStroke(width = 2.dp, color = if (onlineStatus) MaterialTheme.colorScheme.lightGreen else Color.Red),
            modifier = Modifier
                    .padding(16.dp)
                    .shadow(elevation = 4.dp)
    ) {
        Image(
                painter = painterResource(id = drawableId),
                contentDescription = "Profile picture",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean) {
    Column(
            modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
    ) {
        Text(
                text = userName,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.alpha(if (!onlineStatus) 0.7f else 1f)
        )
        Text(
                text = if (onlineStatus) "Active Now" else "Offline",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.alpha(0.7f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardLayoutPreview() {
    MyTheme {
        ProfileCardScreen()
    }
}
