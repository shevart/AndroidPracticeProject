package com.shevart.androidpracticeproject.screen.postslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.shevart.androidpracticeproject.mock.MockDataHolder
import com.shevart.androidpracticeproject.screen.postslist.PostsListViewModel.Event.RefreshPost
import com.shevart.androidpracticeproject.usecase.PostsUseCase
import com.shevart.androidpracticeproject.util.likeClick
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.RuntimeException

class PostsListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val loadPostsUseCase = mock<PostsUseCase.LoadPosts>()
    private val likePostUseCase = mock<PostsUseCase.LikePost>()

    @Before
    fun setUp() {
        whenever(loadPostsUseCase.execute()).thenReturn(Single.just(MockDataHolder.posts))
        whenever(likePostUseCase.execute(any())).thenReturn(Completable.complete())
    }

    @Test
    fun `test initial viewModel state`() {
        // prepare
        whenever(loadPostsUseCase.execute()).thenReturn(Single.never())
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value

        // check
        assertEquals(PostsListViewModel.State.Loading, state)
    }

    @Test
    fun `test ShowPosts viewModel state`() {
        // prepare
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value as PostsListViewModel.State.ShowPosts

        // check
        assertEquals(MockDataHolder.posts, state.posts)
    }

    @Test
    fun `test like click - event`() {
        // prepare
        val viewModel = createViewModel()
        val post = MockDataHolder.posts.first()
        val likedPost = post.likeClick()
        val eventObserver = viewModel.getEventsObservable().test()

        // perform
        viewModel.onLikeButtonClick(post)

        // check
        eventObserver.assertValue { (it as RefreshPost).post == likedPost }
    }

    @Test
    fun `test like click - like useCase`() {
        // prepare
        val viewModel = createViewModel()
        val post = MockDataHolder.posts.first()

        // perform
        viewModel.onLikeButtonClick(post)

        // check
        verify(likePostUseCase, times(1)).execute(post)
    }

    @Test
    fun `test like click - like useCase - onError`() {
        // prepare
        whenever(likePostUseCase.execute(any()))
            .thenReturn(Completable.error(RuntimeException("Something went wrong")))
        val viewModel = createViewModel()
        val post = MockDataHolder.posts.first()
        val eventObserver = viewModel.getEventsObservable().test()

        // perform
        viewModel.onLikeButtonClick(post)

        // check
          assertEquals(post, (eventObserver.values().last() as RefreshPost).post)
        eventObserver.events.size == 2 // 1 for first reaction and 1 for return back
    }

    private fun createViewModel() = PostsListViewModel(
        loadPostsUseCase = loadPostsUseCase,
        likePostUseCase = likePostUseCase
    )
}