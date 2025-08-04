package com.example.todoapp5.navigation

// Navigation route'ları tanımlandı
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Tasks : Screen("tasks")
    object Settings : Screen("settings")
    object TodoDetails : Screen("todo_details/{todoId}") {
        fun createRoute(todoId: String) = "todo_details/$todoId"
    }
}

// Bottom Navigation için kullanılacak ekranlar
val bottomNavScreens = listOf(
    Screen.Home,
    Screen.Tasks,
    Screen.Settings
)