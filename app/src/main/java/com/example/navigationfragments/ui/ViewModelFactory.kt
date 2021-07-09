package com.example.navigationfragments.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navigationfragments.ui.home.add_todo.AddToDoViewModel
import com.example.navigationfragments.ui.data.ToDoRepository
import com.example.navigationfragments.ui.home.HomeViewModel
import com.example.navigationfragments.ui.settings.SettingsViewModel
import java.lang.IllegalArgumentException


class ViewModelFactory(val repository: ToDoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(AddToDoViewModel::class.java)){
            return AddToDoViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(SettingsViewModel::class.java)){
            return SettingsViewModel() as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}