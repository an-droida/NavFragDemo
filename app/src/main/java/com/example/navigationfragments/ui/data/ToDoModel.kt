package com.example.navigationfragments.ui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class ToDoModel(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean?
)