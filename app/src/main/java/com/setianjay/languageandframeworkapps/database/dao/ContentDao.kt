package com.setianjay.languageandframeworkapps.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity

@Dao
interface ContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(content: ContentEntity)

    @Delete
    suspend fun deleteContent(content: ContentEntity)

    @Query("SELECT * FROM table_content WHERE type = :param")
    fun getAllContent(param: String): LiveData<List<ContentEntity>>
}