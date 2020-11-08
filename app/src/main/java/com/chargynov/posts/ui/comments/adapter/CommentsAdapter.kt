package com.chargynov.posts.ui.comments.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chargynov.posts.R
import com.chargynov.posts.models.Comments
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_comments.*
import java.util.*

class CommentsAdapter(
    private var list: MutableList<Comments>,) :
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comments, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addComments(comments: Comments) {
        list.add(comments)
        notifyDataSetChanged()
    }

    class CommentsViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Comments) {
            tvName.text = item.name.split(' ').joinToString(" ") { it.capitalize(Locale.getDefault()) }
            tvEmail.text = item.email
            tvBody.text = item.body
        }
    }
}