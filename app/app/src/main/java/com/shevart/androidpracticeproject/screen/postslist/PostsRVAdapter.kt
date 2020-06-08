package com.shevart.androidpracticeproject.screen.postslist

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shevart.androidpracticeproject.R
import com.shevart.androidpracticeproject.model.Post
import com.shevart.androidpracticeproject.screen.base.BaseRVAdapter
import com.shevart.androidpracticeproject.screen.postslist.PostsRVAdapter.PostViewHolder
import com.shevart.androidpracticeproject.util.inflateView
import com.shevart.androidpracticeproject.util.loadInto

class PostsRVAdapter(
    private val postClickListener: PostClickListener
) : BaseRVAdapter<Post, PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(parent.inflateView(R.layout.item_post)).apply {
            ivPostUserAvatar.setOnClickListener { postClickListener.onUserAvatarClick() }
            tvPostTitleUserName.setOnClickListener { postClickListener.onUserNameClick() }
            tvPostTitleLocation.setOnClickListener { postClickListener.onLocationClick() }
            ivPostTitleOptions.setOnClickListener { postClickListener.onOptionsClick() }
            ivPostLikeButton.setOnClickListener { postClickListener.onLikeClick(getItem(adapterPosition)) }
            ivPostComment.setOnClickListener { postClickListener.onCommentClick() }
            ivPostSendPost.setOnClickListener { postClickListener.onSendPostClick() }
            ivPostBookmark.setOnClickListener { postClickListener.onBookmarkClick() }
        }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.apply {
            ivPostUserAvatar.loadInto(post.userAvatarUrl)
            ivPostImage.loadInto(post.mediaFiles.first().mediaFileUrl)

            tvPostTitleUserName.text = post.userName
            tvPostTitleLocation.text = post.location
            tvPostUserDescription.text = post.postDescription
            tvPostLikesBy.text = "todo"
            tvPostTime.text = post.date.toString()
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPostUserAvatar: ImageView = itemView.findViewById(R.id.ivPostUserAvatar)
        val ivPostImage: ImageView = itemView.findViewById(R.id.ivPostImage)
        val tvPostTitleUserName: TextView = itemView.findViewById(R.id.tvPostTitleUserName)
        val tvPostTitleLocation: TextView = itemView.findViewById(R.id.tvPostTitleLocation)
        val ivPostTitleOptions: ImageView = itemView.findViewById(R.id.ivPostTitleOptions)
        val ivPostLikeButton: ImageView = itemView.findViewById(R.id.ivPostLikeButton)
        val ivPostComment: ImageView = itemView.findViewById(R.id.ivPostComment)
        val ivPostSendPost: ImageView = itemView.findViewById(R.id.ivPostSendPost)
        val ivPostBookmark: ImageView = itemView.findViewById(R.id.ivPostBookmark)
        val tvPostLikesBy: TextView = itemView.findViewById(R.id.tvPostLikesBy)
        val tvPostUserDescription: TextView = itemView.findViewById(R.id.tvPostUserDescription)
        val tvPostTime: TextView = itemView.findViewById(R.id.tvPostTime)
    }

    interface PostClickListener {
        fun onUserAvatarClick()
        fun onUserNameClick()
        fun onLocationClick()
        fun onOptionsClick()
        fun onLikeClick(post: Post)
        fun onCommentClick()
        fun onSendPostClick()
        fun onBookmarkClick()
    }
}