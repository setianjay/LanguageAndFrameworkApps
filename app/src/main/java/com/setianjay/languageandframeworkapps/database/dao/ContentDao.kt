package com.setianjay.languageandframeworkapps.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(content: ContentEntity)

    @Query("SELECT * FROM table_content WHERE type = :param")
    fun getAllContent(param: String): Flow<List<ContentEntity>>
}