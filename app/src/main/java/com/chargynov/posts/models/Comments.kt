package com.chargynov.posts.models

data class Comments(
    var postId: Int,
    var id: Int,
    var name: String,
    var email: String,
    var body: String
)