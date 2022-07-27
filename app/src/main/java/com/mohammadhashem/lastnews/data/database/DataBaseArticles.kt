package com.mohammadhashem.lastnews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohammadhashem.lastnews.data.database.dao.ArticlesDao
import com.mohammadhashem.lastnews.data.database.typeconvertor.Converters
import com.mohammadhashem.lastnews.data.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class DataBaseArticles : RoomDatabase() {
    abstract fun articleDao(): ArticlesDao
}
