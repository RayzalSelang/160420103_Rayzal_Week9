package com.example.a160420103_rayzal_week8.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.a160420103_rayzal_week8.model.Todo
import com.example.a160420103_rayzal_week8.model.TodoDatabase
import kotlinx.coroutines.launch

class DetailTodoViewModel(application: Application) : AndroidViewModel(application) {
    val todoLD = MutableLiveData<Todo>()

    fun addTodo(list: List<Todo>) {
        viewModelScope.launch {
            val db = TodoDatabase.buildDatabase(getApplication())
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    fun fetch(uuid: String) { // Mengubah tipe parameter menjadi String
        viewModelScope.launch {
            val db = TodoDatabase.buildDatabase(getApplication())
            todoLD.postValue(db.todoDao().selectTodo(uuid))
        }
    }

    fun update(title: String, notes: String, priority: Int, uuid: String) { // Mengubah tipe parameter uuid menjadi String
        viewModelScope.launch {
            val db = TodoDatabase.buildDatabase(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }
}