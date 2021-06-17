package com.setianjay.languageandframeworkapps.database.entity

import androidx.annotation.IdRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_content")
data class ContentEntity(
    @PrimaryKey(autoGenerate = false) val title: String,
    @ColumnInfo(name = "poster") @IdRes val poster: Int?,
    @ColumnInfo(name = "detail") val detail: String?,
    @ColumnInfo(name = "type") val type: String?
)