package com.example.navigationfragments.ui.data

import androidx.room.Room
import com.example.navigationfragments.ToDoApp


object DatabaseModule {
    fun provideDatabase(): ToDoDatabase {
        return Room.databaseBuilder(
            ToDoApp.instance!!.applicationContext,
            ToDoDatabase::class.java,
            "todo-database"
        ).build()
    }

    fun provideLogDao(database: ToDoDatabase): Dao {
        return database.dao()
    }
}