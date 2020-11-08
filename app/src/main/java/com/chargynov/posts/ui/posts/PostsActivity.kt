package com.chargynov.posts.ui.posts

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chargynov.posts.R
import com.chargynov.posts.base.BaseActivity
import com.chargynov.posts.extension.showToast
import com.chargynov.posts.models.Post
import com.chargynov.posts.network.Status
import com.chargynov.posts.ui.comments.CommentsActivity
import com.chargynov.posts.ui.posts.adapter.PostsAdapter
import kotlinx.android.synthetic.main.activity_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsActivity : BaseActivity<PostsViewModel>(R.layout.activity_posts), PostsAdapter.OnItemClick {

    override val viewModel by viewModel<PostsViewModel>()
    private lateinit var list: MutableList<Post>
    private lateinit var postsAdapter: PostsAdapter
    private val ID = "id"

    override fun setUpViews() {
        initialization()
        createRecycler()
    }

    override fun setUpObservers() {
        subscribeToPosts()
    }

    private fun subscribeToPosts(){
        viewModel.getPosts().observe(this, {
            when(it.status){
                Status.SUCCESS -> setTable(it.data!!)
                Status.ERROR -> showToast(this, it.message.toString())
                Status.LOADING -> showToast(this, getString(R.string.is_loading))
            }
        })
    }

    private fun setTable(posts: ArrayList<Post>) {
        list.addAll(posts)
        postsAdapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    private fun initialization() {
        list = arrayListOf()
        postsAdapter = PostsAdapter(list, this)
        supportActionBar?.title = getString(R.string.posts)
    }

    private fun createRecycler() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(this, CommentsActivity::class.java)
        intent.putExtra(ID, id)
        intent.flags = (Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}