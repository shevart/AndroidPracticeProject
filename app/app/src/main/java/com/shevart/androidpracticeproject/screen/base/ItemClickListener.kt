package com.shevart.androidpracticeproject.screen.base

import android.view.View

@FunctionalInterface
interface ItemClickListener<M> {
    fun onItemClick(item: M, position: Int, view: View? = null)
}