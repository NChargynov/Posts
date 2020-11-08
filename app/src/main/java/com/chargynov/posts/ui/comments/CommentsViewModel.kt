package com.chargynov.posts.ui.comments

import androidx.lifecycle.LiveData
import com.chargynov.posts.base.BaseViewModel
import com.chargynov.posts.models.ListComments
import com.chargynov.posts.network.Resource
import com.chargynov.posts.repository.PostsRepository

class CommentsViewModel(private val postsRepository: PostsRepository) : BaseViewModel() {

    fun getComments(id: Int): LiveData<Resource<ListComments>>{
        return postsRepository.getComments(id)
    }
}