package com.example.todoapp5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp5.ui.theme.TodoScreen
import com.example.todoapp5.ui.theme.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val viewModel: TodoViewModel = viewModel()
                TodoScreen(viewModel = viewModel)
            }
        }
    }
}