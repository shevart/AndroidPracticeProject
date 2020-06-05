package com.shevart.androidpracticeproject.model

data class PostLikesData(
    val totalLikeCounts: Long,
    val friendsLikes: List<String>,
    // if use liked own post - true, otherwise false
    val isUserCheater: Boolean
)