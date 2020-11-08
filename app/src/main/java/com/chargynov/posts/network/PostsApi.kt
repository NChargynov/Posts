package com.chargynov.posts.network

import com.chargynov.posts.models.ListComments
import com.chargynov.posts.models.ListPosts
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {

    @GET("posts")
    suspend fun getPosts(): ListPosts

    @GET("comments")
    suspend fun getComments(
        @Query("postId") id: Int
    ): ListComments
}