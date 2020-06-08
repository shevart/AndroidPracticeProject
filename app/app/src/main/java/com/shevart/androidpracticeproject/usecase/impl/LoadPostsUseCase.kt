package com.shevart.androidpracticeproject.usecase.impl

import com.shevart.androidpracticeproject.mock.MockDataHolder
import com.shevart.androidpracticeproject.model.Post
import com.shevart.androidpracticeproject.usecase.PostsUseCase
import com.shevart.androidpracticeproject.util.subscribeOnIoObserveOnMain
import io.reactivex.Single
import java.util.concurrent.TimeUnit.SECONDS

class LoadPostsUseCase : PostsUseCase.LoadPosts {
    override fun execute(): Single<List<Post>> =
        Single.just(MockDataHolder.posts)
            .delay(1, SECONDS)
            .subscribeOnIoObserveOnMain()
}