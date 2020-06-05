package com.shevart.androidpracticeproject.model

data class Post(
    val date: Long,
    val userAvatarUrl: String,
    val userName: String,
    val location: String,
    val likesData: PostLikesData,
    val postDescription: String,
    val mediaFiles: List<Media>
)