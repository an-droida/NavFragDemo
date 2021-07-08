package com.example.navigationfragments.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationfragments.ui.data.ToDoModel
import com.example.navigationfragments.ui.data.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class HomeViewModel (
    val repository: ToDoRepository
) : ViewModel(){

    val isFavoriteCall: MutableLiveData<Boolean> = MutableLiveData()

    val todoList: MutableLiveData<List<ToDoModel>> = MutableLiveData()

    init {
        loadAllToDos()
    }


    fun loadAllToDos() = CoroutineScope(IO).launch {
        val response = repository.getAllToDos()
        todoList.postValue(response)
    }

    fun deleteToDo(todoId: Long) {
        CoroutineScope(IO).launch {
            repository.deleteToDo(todoId)
            loadAllToDos()
        }
    }

    fun setFavorite(todoId: Long, isFavorite: Boolean) {
        CoroutineScope(IO).launch {
            repository.setFavorite(todoId, isFavorite)
        }
    }

}