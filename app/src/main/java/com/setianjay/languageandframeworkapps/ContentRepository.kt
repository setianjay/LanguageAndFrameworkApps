package com.setianjay.languageandframeworkapps

import androidx.lifecycle.LiveData
import com.setianjay.languageandframeworkapps.database.AppDatabase
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity
import kotlinx.coroutines.flow.Flow


class ContentRepository(
    private val db: AppDatabase
) {

    suspend fun insertContent(content: ContentEntity){
        db.contentDao().insertContent(content)
    }

    fun getContent(type: String): LiveData<List<ContentEntity>>{
        return db.contentDao().getAllContent(type)
    }
}