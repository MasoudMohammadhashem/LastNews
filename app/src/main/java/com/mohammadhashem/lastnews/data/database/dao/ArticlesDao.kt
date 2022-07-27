package com.mohammadhashem.lastnews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.SourceX
import io.reactivex.rxjava3.core.Single

@Dao
abstract class ArticlesDao {

    @Query("SELECT * FROM Article WHERE Source = :source")
    abstract fun getAllCacheArticles(source: SourceX): Single<List<Article>>

    @Query("DELETE FROM Article WHERE Source = :source")
    abstract fun deleteArticles(source: SourceX)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertArticles(article: Article): Single<Long>
}