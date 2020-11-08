package com.chargynov.posts.ui.comments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chargynov.posts.R
import com.chargynov.posts.base.BaseActivity
import com.chargynov.posts.extension.showToast
import com.chargynov.posts.models.Comments
import com.chargynov.posts.network.Status
import com.chargynov.posts.ui.comments.adapter.CommentsAdapter
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_comments.recyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsActivity : BaseActivity<CommentsViewModel>(R.layout.activity_comments) {

    override val viewModel by viewModel<CommentsViewModel>()
    private lateinit var list: MutableList<Comments>
    private lateinit var commentsAdapter: CommentsAdapter
    private val ID = "id"

    override fun setUpViews() {
        initialization()
        createRecycler()
    }

    override fun setUpObservers() {
        subscribeToComments()
    }

    private fun subscribeToComments() {
        if (intent != null) {
            val id = intent.getIntExtra(ID, 1)
            viewModel.getComments(id).observe(this, {
                when (it.status) {
                    Status.SUCCESS -> setTable(it.data!!)
                    Status.ERROR -> showToast(this, it.message.toString())
                    Status.LOADING -> showToast(this, getString(R.string.is_loading))
                }
            })
        }
    }

    private fun initialization() {
        list = mutableListOf()
        commentsAdapter = CommentsAdapter(list)
        supportActionBar?.title = getString(R.string.comments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun createRecycler() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setTable(comments: MutableList<Comments>) {
        list.addAll(comments)
        commentsAdapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    @SuppressLint("InflateParams")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_comments -> {
                createAlertDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createAlertDialog() {
        val alertDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        alertDialog.setTitle(getString(R.string.add_new_comments))
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_field, null)
        val editName = dialogLayout.findViewById<EditText>(R.id.editName)
        val editEmail = dialogLayout.findViewById<EditText>(R.id.editEmail)
        val editComments = dialogLayout.findViewById<EditText>(R.id.editComments)
        alertDialog.setView(dialogLayout)
        alertDialog.setPositiveButton(getString(R.string.add)) { dialogInterface, i ->
            showToast(this, getString(R.string.add_is_successful))
            commentsAdapter.addComments(Comments(
                0, 0, editName.text.toString(), editEmail.text.toString(), editComments.text.toString()))
        }
        alertDialog.setNegativeButton(getString(R.string.cancel)) { dialogInterface, i ->
            showToast(this, getString(R.string.comments_is_failure))
        }
        alertDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tool_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}