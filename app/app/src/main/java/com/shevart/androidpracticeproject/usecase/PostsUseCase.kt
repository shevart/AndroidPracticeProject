package com.shevart.androidpracticeproject.usecase

import com.shevart.androidpracticeproject.model.Post
import io.reactivex.Single

interface PostsUseCase {
    interface LoadPosts {
        fun execute(): Single<List<Post>>
    }
}