package com.example.jetpackcomposeapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.jetpackcomposeapplication.ui.theme.MyTheme
import com.example.jetpackcomposeapplication.ui.theme.lightGreen

class ProfileCardLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                UsersApplication()
            }
        }
    }
}

@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable(route = "users_list") {
            ProfileCardScreen(userProfiles, navController)
        }
        composable(
            route = "user_details/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            UserProfileDetailsScreen(navBackStackEntry.arguments!!.getInt("userId"), navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileCardScreen(userProfiles: List<UserProfile>, navController: NavHostController?) {
    Scaffold(topBar = {
        AppBar(
            title = "My Contacts",
            icon = Icons.Default.Home
        ) {}
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn() {
                items(userProfiles) { userProfile ->
                    ProfileCard(userProfile = userProfile) {
                        navController?.navigate("user_details/${userProfile.id}")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileDetailsScreen(userId: Int, navController: NavHostController?) {
    var userProfile = userProfileList.first { userProfile -> userId == userProfile.id }
    Scaffold(topBar = {
        AppBar(
            title = "Contact Details",
            icon = Icons.Default.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(userProfile.pictureUrl, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = icon,
                "Appbar Icon",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClickAction.invoke() })
            )
        },
        title = { Text(text = title) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan)
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable(onClick = { clickAction.invoke() }),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus) MaterialTheme.colorScheme.lightGreen else Color.Red
        ),
        modifier = Modifier
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        // Using Coil
        AsyncImage(
            model = pictureUrl,
            contentDescription = "Profile picture description",
            modifier = Modifier.size(imageSize)
        )
        // Using Image
        /*        Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = "Profile picture",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )*/
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
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
fun UserProfileDetailsScreenPreview() {
    MyTheme {
        UserProfileDetailsScreen(userId = 0, null)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardScreenPreview() {
    MyTheme {
        ProfileCardScreen(userProfiles = userProfileList, null)
    }
}
