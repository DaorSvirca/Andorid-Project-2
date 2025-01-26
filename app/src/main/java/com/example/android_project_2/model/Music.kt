package com.example.android_project_2.model

data class Music(
    var id: Int,
    var title: String,
    var body: String
)


data class MusicResponse(
    val posts : List<Music>
)