package com.setianjay.languageandframeworkapps.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.setianjay.languageandframeworkapps.database.dao.ContentDao
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity

@Database(entities = [ContentEntity::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun contentDao(): ContentDao

}