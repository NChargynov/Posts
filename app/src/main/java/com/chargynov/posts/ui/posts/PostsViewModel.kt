package com.chargynov.posts.ui.posts

import androidx.lifecycle.LiveData
import com.chargynov.posts.base.BaseViewModel
import com.chargynov.posts.models.ListPosts
import com.chargynov.posts.network.Resource
import com.chargynov.posts.repository.PostsRepository

class PostsViewModel(private val postsRepository: PostsRepository): BaseViewModel(){

     fun getPosts(): LiveData<Resource<ListPosts>> {
        return postsRepository.getPosts()
    }
}