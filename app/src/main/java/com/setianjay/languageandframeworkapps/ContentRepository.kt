package com.setianjay.languageandframeworkapps

import com.setianjay.languageandframeworkapps.database.AppDatabase
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity
import kotlinx.coroutines.flow.Flow


class ContentRepository(
    private val db: AppDatabase
) {

    val allContentLanguages: Flow<List<ContentEntity>> = db.contentDao().getAllContent("languages")
    val allContentFramework: Flow<List<ContentEntity>> = db.contentDao().getAllContent("frameworks")

    suspend fun insertContent(content: ContentEntity){
        db.contentDao().insertContent(content)
    }
}