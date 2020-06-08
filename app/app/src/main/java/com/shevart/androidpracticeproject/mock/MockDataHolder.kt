package com.shevart.androidpracticeproject.mock

import com.shevart.androidpracticeproject.model.Media
import com.shevart.androidpracticeproject.model.Post
import com.shevart.androidpracticeproject.model.PostLikesData
import java.util.*
import kotlin.collections.ArrayList

object MockDataHolder {
    private val _posts: List<Post> = mutableListOf(
        Post(
            date = Date().time,
            userAvatarUrl = "https://motor.ru/thumb/200x0/filters:quality(75):no_upscale()/imgs/2020/06/03/14/3940003/ee3f820dfb1a5fea94b3bf3034c6a152654eb84b.jpg",
            userName = "milena_thebrowbar",
            location = "The Brow Bar Kharkiv",
            likesData = PostLikesData(
                totalLikeCounts = 1,
                userLike = false,
                friendsLikes = listOf("Vasya", "Petya")
            ),
            postDescription = "Wow! My post description with great person quote!",
            mediaFiles = listOf(
                Media(
                    video = false,
                    mediaFileUrl = "https://motor.ru/thumb/720x0/filters:quality(75):no_upscale()/imgs/2020/06/03/14/3939987/22bacd7b56971628561cbdc14140dc42e6a68929.jpg"
                )
            )
        ),
        Post(
            date = Date().time,
            userAvatarUrl = "https://motor.ru/thumb/200x0/filters:quality(75):no_upscale()/imgs/2020/06/03/14/3939995/a8cd783b4322462f6cc5ae12bcb5010320959891.jpg",
            userName = "milena_thebrowbar",
            location = "Imola",
            likesData = PostLikesData(
                totalLikeCounts = 1,
                userLike = false,
                friendsLikes = listOf("Vasya", "Petya")
            ),
            postDescription = "There is my post description for this post.",
            mediaFiles = listOf(
                Media(
                    video = false,
                    mediaFileUrl = "https://motor.ru/thumb/1816x0/filters:quality(75):no_upscale()/imgs/2020/06/03/14/3940003/ee3f820dfb1a5fea94b3bf3034c6a152654eb84b.jpg"
                )
            )
        )
    )

    val posts: List<Post> = _posts



}