package com.chargynov.posts.repository

import androidx.lifecycle.liveData
import com.chargynov.posts.network.PostsApi
import com.chargynov.posts.network.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class PostsRepository(private val api: PostsApi) {

    fun getPosts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getPosts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred"))
        }
    }

    fun getComments(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getComments(id)))
        } catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred"))
        }
    }
}