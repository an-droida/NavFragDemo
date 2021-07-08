package com.example.navigationfragments.ui.data



class ToDoRepository(val db: Dao) {

    fun getAllToDos() = db.getAll()
    fun addToDo(todoModel:ToDoModel) = db.addToDo(todoModel)
    fun editToDo(title:String,description:String,todoId:Long) = db.updateToDo(title,description,todoId)
    fun deleteToDo(todoId: Long) = db.delete(todoId)
    fun loadById(todoId: Long) = db.loadByIds(todoId)
    fun setFavorite(todoId: Long,isFavorite:Boolean) = db.addFavorites(todoId,isFavorite)
}