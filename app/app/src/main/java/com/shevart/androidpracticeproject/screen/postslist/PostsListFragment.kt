package com.shevart.androidpracticeproject.screen.postslist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.shevart.androidpracticeproject.R
import com.shevart.androidpracticeproject.model.Post
import com.shevart.androidpracticeproject.screen.base.AbsMvvmFragment
import com.shevart.androidpracticeproject.screen.base.BaseFragment
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.Event
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.Event.RefreshPost
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.State
import com.shevart.androidpracticeproject.util.gone
import com.shevart.androidpracticeproject.util.observeLiveDataForceNonNull
import com.shevart.androidpracticeproject.util.visible
import kotlinx.android.synthetic.main.fragment_posts_list.*

class PostsListFragment : AbsMvvmFragment<PostsListViewModel>() {
    private lateinit var adapter: PostsRVAdapter
    private val postItemClickListener = object : PostsRVAdapter.PostClickListener {
        override fun onUserAvatarClick() {
            showToast("onUserAvatarClick")
        }

        override fun onUserNameClick() {
            showToast("onUserAvatarClick")
        }

        override fun onLocationClick() {
            showToast("onLocationClick")
        }

        override fun onOptionsClick() {
            showToast("onOptionsClick")
        }

        override fun onLikeClick(post: Post) {
            viewModel.onLikeButtonClick(post)
        }

        override fun onCommentClick() {
            showToast("onCommentClick")
        }

        override fun onSendPostClick() {
            showToast("onSendPostClick")
        }

        override fun onBookmarkClick() {
            showToast("onBookmarkClick")
        }
    }

    override fun provideLayoutResId() = R.layout.fragment_posts_list

    override fun provideViewModelClass() = PostsListViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PostsRVAdapter(postItemClickListener)
        rvPostList.adapter = adapter
        rvPostList.layoutManager = LinearLayoutManager(requireContext())

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
        when(event) {
            is RefreshPost -> refreshPost(event)
        }
    }

    private fun showLoading() {
        TransitionManager.beginDelayedTransition(flPostsListRoot)
        vwLoading.visible()
        rvPostList.gone()
    }

    private fun showPostsList(state: State.ShowPosts) {
        TransitionManager.beginDelayedTransition(flPostsListRoot)
        vwLoading.gone()
        rvPostList.visible()
        adapter.updateItems(state.posts)
    }

    private fun refreshPost(event: RefreshPost) {
        adapter.updateItem(event.post, adapter.getItemPosition(event.post))
    }
}