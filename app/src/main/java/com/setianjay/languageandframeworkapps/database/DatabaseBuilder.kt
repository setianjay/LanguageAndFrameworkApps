package com.setianjay.languageandframeworkapps.database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase{
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "app_db"
            ).build()

            INSTANCE = instance
            instance
        }
    }

//    private var instance: AppDatabase? = null
//
//    fun getInstance(context: Context): AppDatabase{
//        if (instance == null){
//            synchronized(AppDatabase::class){
//                instance = buildRoomDB(context)
//            }
//        }
//        return instance!!
//    }
//
//    private fun buildRoomDB(context: Context): AppDatabase{
//        return Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            "app_db"
//        ).build()
//    }

}