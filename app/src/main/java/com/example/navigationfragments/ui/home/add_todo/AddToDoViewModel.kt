package com.example.navigationfragments.ui.home.add_todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationfragments.ui.data.ToDoDatabase
import com.example.navigationfragments.ui.data.ToDoModel
import com.example.navigationfragments.ui.data.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddToDoViewModel(
    private val repository: ToDoRepository
) : ViewModel() {

    val loadToDoModel: MutableLiveData<ToDoModel> = MutableLiveData()

    fun addToDo(toDoModel: ToDoModel) {
        CoroutineScope(IO).launch {
            repository.addToDo(toDoModel)
        }
    }

    fun editToDo(title: String, description: String, todoId: Long) {
        CoroutineScope(IO).launch {
            repository.editToDo(title, description, todoId)
        }
    }

    fun getToDoById(id: Long) {
        CoroutineScope(IO).launch {
            loadToDoModel.postValue(repository.loadById(id))
        }
    }
}