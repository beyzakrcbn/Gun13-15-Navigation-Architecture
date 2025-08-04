package com.example.todoapp5.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp5.navigation.Screen
import com.example.todoapp5.ui.theme.TodoViewModel


@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TodoViewModel
) {
    val todoList by viewModel.todoList.collectAsState()

    Column(Modifier.padding(16.dp)) {
        Text(
            text = "Tüm Görevler",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        var newTask by remember { mutableStateOf("") }
        var newDescription by remember { mutableStateOf("") }
        var newDate by remember { mutableStateOf("") }
        var newTime by remember { mutableStateOf("") }
        var showAddDialog by remember { mutableStateOf(false) }

        // Hızlı ekleme butonu
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
                placeholder = { Text("Hızlı görev ekle...") }
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
                Text("Ekle", color = Color.White)
            }

            // Detaylı ekleme butonu
            Button(
                onClick = { showAddDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Detaylı Görev Ekle")
            }
            // Detaylı ekleme dialog'u
            if (showAddDialog) {
                AlertDialog(
                    onDismissRequest = { showAddDialog = false },
                    title = { Text("Yeni Görev Ekle") },
                    text = {
                        Column {
                            TextField(
                                value = newTask,
                                onValueChange = { newTask = it },
                                label = { Text("Görev Başlığı") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )
                            TextField(
                                value = newDescription,
                                onValueChange = { newDescription = it },
                                label = { Text("Açıklama") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )
                            TextField(
                                value = newDate,
                                onValueChange = { newDate = it },
                                label = { Text("Tarih (ör: 15 Mart 2024)") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )
                            TextField(
                                value = newTime,
                                onValueChange = { newTime = it },
                                label = { Text("Saat (ör: 14:30)") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                if (newTask.isNotBlank()) {
                                    viewModel.addTask(newTask, newDescription, newDate, newTime)
                                    newTask = ""
                                    newDescription = ""
                                    newDate = ""
                                    newTime = ""
                                    showAddDialog = false
                                }
                            }
                        ) {
                            Text("Ekle")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showAddDialog = false }) {
                            Text("İptal")
                        }
                    }
                )
            }


