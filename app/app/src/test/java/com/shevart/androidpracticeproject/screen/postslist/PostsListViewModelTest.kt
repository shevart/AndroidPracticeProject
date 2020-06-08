package com.shevart.androidpracticeproject.screen.postslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PostsListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @Test
    fun `test initial viewModel state`() {
        // prepare
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value

        // check
        assertEquals(PostsListViewModel.State.Loading, state)
    }

    private fun createViewModel() = PostsListViewModel()
}