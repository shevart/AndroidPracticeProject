package com.shevart.androidpracticeproject.model

data class PostLikesData(
    val totalLikeCounts: Long,
    val friendsLikes: List<String>,
    // if user liked post - true, otherwise false
    val userLike: Boolean
)