package com.shevart.androidpracticeproject.usecase

import com.shevart.androidpracticeproject.model.Post
import io.reactivex.Completable
import io.reactivex.Single

interface PostsUseCase {
    interface LoadPosts {
        fun execute(): Single<List<Post>>
    }

    interface LikePost {
        fun execute(post: Post): Completable
    }
}