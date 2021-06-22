package com.setianjay.languageandframeworkapps

import androidx.lifecycle.LiveData
import com.setianjay.languageandframeworkapps.database.AppDatabase
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity


class ContentRepository(
    private val db: AppDatabase
) {

    suspend fun insertContent(content: ContentEntity){
        db.contentDao().insertContent(content)
    }

    suspend fun deleteContent(content: ContentEntity){
        db.contentDao().deleteContent(content)
    }

    fun getContent(type: String): LiveData<List<ContentEntity>>{
        return db.contentDao().getAllContent(type)
    }
}