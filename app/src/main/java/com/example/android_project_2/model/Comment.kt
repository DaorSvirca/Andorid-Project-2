package com.example.android_project_2.model

data class Comment(
    var id: Int,
    var body: String,
    var postId: Int,
    var likes: Int
)
