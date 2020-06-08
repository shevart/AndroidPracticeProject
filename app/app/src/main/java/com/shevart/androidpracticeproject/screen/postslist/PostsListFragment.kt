package com.shevart.androidpracticeproject.screen.postslist

import android.os.Bundle
import android.view.View
import com.shevart.androidpracticeproject.R
import com.shevart.androidpracticeproject.screen.base.AbsMvvmFragment
import com.shevart.androidpracticeproject.screen.base.BaseFragment
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.Event
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.State
import com.shevart.androidpracticeproject.util.observeLiveDataForceNonNull

class PostsListFragment : AbsMvvmFragment<PostsListViewModel>() {
    override fun provideLayoutResId() = R.layout.fragment_posts_list

    override fun provideViewModelClass() = PostsListViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveDataForceNonNull(viewModel.getStateLiveData(), this::renderState)
        viewModel.getEventsObservable()
            .subscribe(
                this::handleEvent,
                this::defaultHandleException
            )
            .disposeOnDestroyView()


    }

    private fun renderState(state: State) {
        when(state) {
            State.Loading -> showLoading()
            is State.ShowPosts -> showPostsList(state)
        }
    }

    private fun handleEvent(event: Event) {

    }

    private fun showLoading() {

    }

    private fun showPostsList(state: State.ShowPosts) {

    }
}