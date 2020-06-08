package com.shevart.androidpracticeproject.util

import com.shevart.androidpracticeproject.model.Post

// return copy of post with reverted user like
fun Post.likeClick() = this.copy(
    likesData = this.likesData.copy(
        userLike = !this.likesData.userLike
    )
)