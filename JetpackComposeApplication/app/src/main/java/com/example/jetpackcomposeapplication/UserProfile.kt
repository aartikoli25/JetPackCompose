package com.example.jetpackcomposeapplication

data class UserProfile constructor(val name: String, val status: Boolean, val drawableId: Int)

val userProfileList = arrayListOf(
        UserProfile(name = "Janice Zhou", status = true, drawableId = R.drawable.ic_profile_picture_1),
        UserProfile(name = "Mukesh Gupta", status = true, drawableId = R.drawable.ic_profile_picture_2),
        UserProfile(name = "Luis Peter", status = false, drawableId = R.drawable.ic_profile_picture_3),
        UserProfile(name = "Selena Gomz", status = true, drawableId = R.drawable.ic_profile_picture_4),
        UserProfile(name = "Ily Jonas ", status = true, drawableId = R.drawable.ic_profile_picture_5),
        UserProfile(name = "Harry Fan", status = false, drawableId = R.drawable.ic_profile_picture_6),
        UserProfile(name = "Dia Loral", status = true, drawableId = R.drawable.ic_profile_picture_7)
)
