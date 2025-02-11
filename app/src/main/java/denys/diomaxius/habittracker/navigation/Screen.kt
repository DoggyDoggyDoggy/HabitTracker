package denys.diomaxius.habittracker.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("mainScreen")
    object AddHabitTable : Screen("addHabitTable")
    object EditHabitTable : Screen("editHabitTable")
    object Archive : Screen("archive")
}