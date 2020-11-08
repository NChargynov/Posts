package com.chargynov.posts.di

import com.chargynov.posts.network.RetrofitClient
import com.chargynov.posts.repository.PostsRepository
import com.chargynov.posts.ui.comments.CommentsViewModel
import com.chargynov.posts.ui.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var postsModule = module {
    single { RetrofitClient().providePosts() }
    viewModel { PostsViewModel(get()) }
    viewModel { CommentsViewModel(get()) }
    factory { PostsRepository(get()) }
}