package com.shevart.androidpracticeproject.usecase.impl

import com.shevart.androidpracticeproject.model.Post
import com.shevart.androidpracticeproject.usecase.PostsUseCase
import com.shevart.androidpracticeproject.util.subscribeOnIoObserveOnMain
import io.reactivex.Completable
import java.util.concurrent.TimeUnit.SECONDS

class LikePostUseCase : PostsUseCase.LikePost {
    override fun execute(post: Post) = Completable.fromRunnable {
        // place for request
    }
        .delay(1, SECONDS)
        .subscribeOnIoObserveOnMain()
}