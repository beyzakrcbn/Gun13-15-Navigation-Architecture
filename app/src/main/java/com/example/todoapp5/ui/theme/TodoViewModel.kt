package com.example.todoapp5.ui.theme


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class TodoItem(val title: String, val isDone: Boolean = false)

class TodoViewModel : ViewModel() {

    private val _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoList: StateFlow<List<TodoItem>> get() = _todoList

    fun addTask(title: String) {

        val currentList = _todoList.value.toMutableList()
        currentList.add(TodoItem(title = title))
        _todoList.value = currentList
    }

    fun toggleTask(task: TodoItem) {

        _todoList.value = _todoList.value.map {
            if (it == task) it.copy(isDone = !it.isDone) else it
        }
    }
    fun removeTask(item: TodoItem) {

        _todoList.value = _todoList.value - item
    }
}