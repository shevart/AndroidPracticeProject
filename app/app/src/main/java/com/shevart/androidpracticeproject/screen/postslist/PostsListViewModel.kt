package com.shevart.androidpracticeproject.screen.postslist

import com.shevart.androidpracticeproject.model.Post
import com.shevart.androidpracticeproject.screen.base.AbsStateViewModel
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.Event
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.State
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.State.ShowPosts
import com.shevart.androidpracticeproject.usecase.PostsUseCase
import com.shevart.androidpracticeproject.usecase.impl.LoadPostsUseCase

class PostsListViewModel(
    private val loadPostsUseCase: PostsUseCase.LoadPosts = LoadPostsUseCase()
) : AbsStateViewModel<State, Event>(

) {
    init {
        setState(State.Loading)
        loadPosts()
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

    sealed class State {
        object Loading : State()
        data class ShowPosts(
            val posts: List<Post>
        ) : State()
    }

    sealed class Event
}