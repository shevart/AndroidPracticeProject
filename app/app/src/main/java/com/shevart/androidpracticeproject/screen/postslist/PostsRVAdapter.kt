package com.shevart.androidpracticeproject.screen.postslist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shevart.androidpracticeproject.model.Post
import com.shevart.androidpracticeproject.screen.base.BaseRVAdapter
import com.shevart.androidpracticeproject.screen.postslist.PostsRVAdapter.PostViewHolder

class PostsRVAdapter : BaseRVAdapter<Post, PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        TODO()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        TODO()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}