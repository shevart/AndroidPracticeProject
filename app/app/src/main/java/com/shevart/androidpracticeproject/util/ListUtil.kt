package com.shevart.androidpracticeproject.util

fun <M> MutableList<M>.replaceAll(items: List<M>) {
    this.clear()
    this.addAll(items)
}