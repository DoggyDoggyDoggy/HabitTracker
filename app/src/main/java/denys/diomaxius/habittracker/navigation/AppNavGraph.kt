package denys.diomaxius.habittracker.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.habittracker.ui.screen.addHabitTable.AddHabitTable
import denys.diomaxius.habittracker.ui.screen.main.MainScreen
import denys.diomaxius.habittracker.ui.screen.main.MainScreenViewModel

@Composable
fun AppNavGraph(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(
            route = Screen.MainScreen.route,
        ) {
            MainScreen(
                navHostController = navHostController
            )
        }

        composable(
            route = Screen.AddHabitTable.route,
        ) {
            AddHabitTable(
                navHostController = navHostController
            )
        }
    }
}