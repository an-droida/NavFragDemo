package com.example.navigationfragments.ui.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.navigationfragments.ToDoApp

@Database(entities = [ToDoModel::class], version = 2)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun dao(): Dao

    companion object{
        val db = Room.databaseBuilder(
            ToDoApp.instance!!.applicationContext,
            ToDoDatabase::class.java, "todo-database"
        ).build()
    }

}

