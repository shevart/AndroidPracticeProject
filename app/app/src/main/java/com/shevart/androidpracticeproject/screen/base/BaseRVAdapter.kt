package com.shevart.androidpracticeproject.screen.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.shevart.androidpracticeproject.util.inflateView
import com.shevart.androidpracticeproject.util.replaceAll

abstract class BaseRVAdapter<M, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    private val items = ArrayList<M>()
    private var itemClickListener: ItemClickListener<M>? = null

    override fun getItemCount() = items.size

    fun getItem(index: Int) = items[index]

    fun getItems(): List<M> = items

    fun isEmpty() = items.isEmpty()

    fun getItemPosition(item: M): Int {
        if (!items.contains(item)) {
            return -1
        }

        for (i in 0..items.size) {
            if (items[i] == item) {
                return i
            }
        }

        return -1
    }

    open fun updateItems(items: List<M>, notify: Boolean = true) {
        this.items.replaceAll(items)
        if (notify) {
            notifyDataSetChanged()
        }
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun updateItem(item: M, index: Int): Boolean {
        if (index < 0 || items.size < index) {
            return false
        }

        val oldItem = if (items.size == 0) {
            null
        } else {
            items.removeAt(index)
        }

        if (oldItem != null) {
            items.add(index, item)
            notifyItemChanged(index)
            return true
        }
        return false
    }

    fun inflate(parent: ViewGroup, @LayoutRes layoutRes: Int): View {
        return parent.inflateView(layoutRes)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener<M>) {
        this.itemClickListener = itemClickListener
        if (items.isNotEmpty()) {
            notifyDataSetChanged()
        }
    }

    fun getItemClickListener() = itemClickListener

    fun removeItemClickListener() {
        this.itemClickListener = null
    }

    fun remove(item: M): Boolean {
        val index = items.indexOf(item)
        if (index != -1) {
            items.removeAt(index)
            notifyItemRemoved(index)
            return true
        }
        return false
    }

    fun remove(index: Int): Boolean {
        if (index != -1) {
            items.removeAt(index)
            notifyItemRemoved(index)
            return true
        }
        return false
    }

    protected fun BaseRVAdapter<M, VH>.throwIllegalViewType(position: Int): Nothing {
        throw IllegalArgumentException("Handle it! Provide viewType for $position position!")
    }

    protected fun BaseRVAdapter<M, VH>.throwCreateViewHolderIllegalViewType(viewType: Int): Nothing {
        throw IllegalArgumentException("Handle it! Provide viewHolder for $viewType viewType!")
    }

    protected fun BaseRVAdapter<M, VH>.throwBindViewHolderIllegalViewType(position: Int) {
        throw IllegalArgumentException(
            "Handle it! Handle onBindViewHolder() for ${getItemViewType(
                position
            )} viewType!"
        )
    }
}