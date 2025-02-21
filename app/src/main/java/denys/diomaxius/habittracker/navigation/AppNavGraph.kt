package denys.diomaxius.habittracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import denys.diomaxius.habittracker.ui.screen.addHabitTable.AddHabitTable
import denys.diomaxius.habittracker.ui.screen.archive.Archive
import denys.diomaxius.habittracker.ui.screen.editHabitTable.EditHabitTable
import denys.diomaxius.habittracker.ui.screen.main.MainScreen
import denys.diomaxius.habittracker.ui.screen.onboarding.OnBoardingScreen
import denys.diomaxius.habittracker.ui.screen.tips.TipsScreen
import denys.diomaxius.habittracker.ui.screen.weekly.WeeklyScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.OnBoarding.route
    ) {
        composable(
            route = Screen.MainScreen.route,
        ) {
            MainScreen(
                navHostController = navHostController
            )
        }

        composable(
            route = "${Screen.AddHabitTable.route}?habitId={habitId}",
            arguments = listOf(navArgument("habitId") {
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            val habitId = backStackEntry.arguments?.getString("habitId")?.toIntOrNull()
            AddHabitTable(
                navHostController = navHostController,
                habitId = habitId
            )
        }

        composable(
            route = Screen.EditHabitTable.route,
        ) {
            EditHabitTable(
                navHostController = navHostController
            )
        }

        composable(
            route = Screen.Archive.route
        ){
            Archive(navHostController = navHostController)
        }

        composable(
            route = Screen.Weekly.route
        ){
            WeeklyScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.Tips.route
        ) {
            TipsScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.OnBoarding.route
        ) {
            OnBoardingScreen(navHostController = navHostController)
        }
    }
}