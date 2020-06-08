package com.shevart.androidpracticeproject.screen.postslist

import com.shevart.androidpracticeproject.model.Post
import com.shevart.androidpracticeproject.screen.base.AbsStateViewModel
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.Event
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.Event.RefreshPost
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.State
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.State.ShowPosts
import com.shevart.androidpracticeproject.usecase.PostsUseCase
import com.shevart.androidpracticeproject.usecase.impl.LikePostUseCase
import com.shevart.androidpracticeproject.usecase.impl.LoadPostsUseCase
import com.shevart.androidpracticeproject.util.likeClick
import java.lang.Exception

class PostsListViewModel(
    private val loadPostsUseCase: PostsUseCase.LoadPosts = LoadPostsUseCase(),
    private val likePostUseCase: PostsUseCase.LikePost = LikePostUseCase()
) : AbsStateViewModel<State, Event>(

) {
    init {
        setState(State.Loading)
        loadPosts()
    }

    fun onLikeButtonClick(post: Post) {
        sendEvent(RefreshPost(post.likeClick()))
        likePostUseCase.execute(post)
            .subscribe(
                {
                    // do nothing
                },
                { e: Throwable ->
                    onPostLikeFailed(post, e)
                }
            )
            .addToClearedDisposable()
    }

    private fun loadPosts() {
        loadPostsUseCase.execute()
            .subscribe(
                this::onPostsLoaded,
                this::defaultHandleException
            )
            .addToClearedDisposable()
    }

    private fun onPostsLoaded(posts: List<Post>) {
        setState(ShowPosts(posts = posts))
    }

    private fun onPostLikeFailed(post: Post, e: Throwable) {
//        defaultHandleException(e)
        sendEvent(RefreshPost(post))
    }

    sealed class State {
        object Loading : State()
        data class ShowPosts(
            val posts: List<Post>
        ) : State()
    }

    sealed class Event {
        class RefreshPost(val post: Post): Event()
    }
}