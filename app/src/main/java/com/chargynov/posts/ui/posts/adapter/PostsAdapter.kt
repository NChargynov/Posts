package com.chargynov.posts.ui.posts.adapter

import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chargynov.posts.R
import com.chargynov.posts.models.Post
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_posts.*
import java.util.*

class PostsAdapter(
    private var list: MutableList<Post>,
    private val onClick: OnItemClick
) :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_posts, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick.onItemClick(list[position].id)
        }
    }

    override fun getItemCount(): Int = list.size

    class PostsViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Post) {
            tvTitle.text =
                item.title.split(' ').joinToString(" ") { it.capitalize(Locale.getDefault()) }
            tvBody.text = item.body.split(' ').joinToString(" ") { it.capitalize(Locale.ROOT) }
        }
    }

    interface OnItemClick {
        fun onItemClick(id: Int)
    }
}