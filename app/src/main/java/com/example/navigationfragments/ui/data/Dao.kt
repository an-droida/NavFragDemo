package com.example.navigationfragments.ui.data

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Query("SELECT * FROM ToDoModel")
    fun getAll(): List<ToDoModel>

    @Query("SELECT * FROM ToDoModel WHERE id =:todoId")
    fun loadByIds(todoId: Long): ToDoModel

    @Insert
    fun addToDo(vararg users: ToDoModel)

    @Query("DELETE FROM todomodel WHERE id=:todoId")
    fun delete(todoId: Long)

    @Query("UPDATE todomodel SET title =:title, description=:description WHERE id=:todoId")
    fun updateToDo(title:String, description:String, todoId:Long)


    @Query("UPDATE todomodel SET isFavorite =:isFavorite WHERE id=:todoId")
    fun addFavorites(todoId:Long,isFavorite:Boolean)

}