package com.example.navigationfragments

import android.app.Application

class ToDoApp:Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion object {
        var instance: ToDoApp? = null
    }
}