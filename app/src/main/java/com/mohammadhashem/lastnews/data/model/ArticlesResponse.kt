package com.mohammadhashem.lastnews.data.model


data class ArticlesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)