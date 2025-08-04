package navigation

sealed class Screen(val route: String) {
    object TodoList : Screen("todo_list")
    object TodoDetail : Screen("todo_detail/{taskTitle}") {
        fun createRoute(taskTitle: String) = "todo_detail/$taskTitle"
    }
}
