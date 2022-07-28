package com.mohammadhashem.lastnews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceX,
    val title: String,
    @PrimaryKey  //This is not a good choice but this is the only unit field
    val url: String,
    val urlToImage: String
)