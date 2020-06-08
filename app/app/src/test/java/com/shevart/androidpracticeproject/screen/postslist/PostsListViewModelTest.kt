package com.shevart.androidpracticeproject.screen.postslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.shevart.androidpracticeproject.mock.MockDataHolder
import com.shevart.androidpracticeproject.usecase.PostsUseCase
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PostsListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val loadPostsUseCase = mock<PostsUseCase.LoadPosts>()

    @Before
    fun setUp() {
        whenever(loadPostsUseCase.execute()).thenReturn(Single.just(MockDataHolder.posts))
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

    private fun createViewModel() = PostsListViewModel(
        loadPostsUseCase = loadPostsUseCase
    )
}