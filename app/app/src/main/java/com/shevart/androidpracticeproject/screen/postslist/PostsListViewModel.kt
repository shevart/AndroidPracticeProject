package com.shevart.androidpracticeproject.screen.postslist

import com.shevart.androidpracticeproject.model.Post
import com.shevart.androidpracticeproject.screen.base.AbsStateViewModel
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.Event
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.State

class PostsListViewModel : AbsStateViewModel<State, Event>() {
    init {
        setState(State.Loading)
    }

    sealed class State {
        object Loading: State()
        data class ShowPosts(
            val posts: List<Post>
        ): State()
    }

    sealed class Event
}