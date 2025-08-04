package com.example.todoapp5.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class TodoItem(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val description: String = "",
    val date: String = "",
    val time: String = "",
    val isDone: Boolean = false
)

class TodoViewModel : ViewModel() {

    private val _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoList: StateFlow<List<TodoItem>> = _todoList

    // Basit görev ekleme (sadece başlık)
    fun addTask(title: String) {
        val currentList = _todoList.value.toMutableList()
        currentList.add(TodoItem(title = title))
        _todoList.value = currentList
    }

    // Detaylı görev ekleme (tüm parametreler)
    fun addTask(title: String, description: String, date: String, time: String) {
        val currentList = _todoList.value.toMutableList()
        currentList.add(
            TodoItem(
                title = title,
                description = description,
                date = date,
                time = time
            )
        )
        _todoList.value = currentList
    }

    // ID'ye göre görev bulma
    fun getTodoById(id: String): TodoItem? {
        return _todoList.value.find { it.id == id }
    }

    // Görev güncelleme
    fun updateTodo(updatedTodo: TodoItem) {
        _todoList.value = _todoList.value.map {
            if (it.id == updatedTodo.id) updatedTodo else it
        }
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