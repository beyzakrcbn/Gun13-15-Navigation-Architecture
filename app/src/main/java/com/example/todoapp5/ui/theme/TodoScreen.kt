package com.example.todoapp5.ui.theme


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    val todoList = viewModel.todoList.collectAsState().value

    Column(Modifier.padding(16.dp)) {
        Spacer(Modifier.height(32.dp))
        Text(
            text = "Todo List",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        var newTask by remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newTask,
                onValueChange = { newTask = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Yeni görev gir") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (newTask.isNotBlank()) {
                        viewModel.addTask(newTask)
                        newTask = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    "Ekle",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.White
                    )
                )
            }
        }

        LazyColumn {
            items(todoList) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.isDone,
                        onCheckedChange = { viewModel.toggleTask(item) }
                    )
                    Text(
                        text = item.title,
                        style = TextStyle(
                            textDecoration = if (item.isDone) TextDecoration.LineThrough else TextDecoration.None
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { viewModel.removeTask(item) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Gray
                )
            }
        }
    }
}