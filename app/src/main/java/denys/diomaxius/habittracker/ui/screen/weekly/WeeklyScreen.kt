package denys.diomaxius.habittracker.ui.screen.weekly

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.model.HabitProgress
import denys.diomaxius.habittracker.navigation.Screen
import denys.diomaxius.habittracker.ui.components.topbar.TopBar
import denys.diomaxius.habittracker.ui.components.ViewSwitcher
import denys.diomaxius.habittracker.ui.screen.weekly.donehabits.DoneHabits
import java.time.LocalDate

@Composable
fun WeeklyScreen(
    viewModel: WeeklyViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val doneHabitList by viewModel.doneHabitList.collectAsState()
    val inProgressHabitList by viewModel.inProgressHabitList.collectAsState()
    val dayOfWeek by viewModel.dayOfWeek.collectAsState()
    val habitListIsNotEmpty by viewModel.habitListIsNotEmpty.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                navHostController = navHostController
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            changeDayOfWeek = { viewModel.changeDayOfWeek(it) },
            doneList = doneHabitList,
            navHostController = navHostController,
            listsNotEmpty = habitListIsNotEmpty,
            inProgressHabitList = inProgressHabitList,
            dayOfWeek = dayOfWeek,
            insertProgress = { viewModel.insertProgress(it) }
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Content(
    modifier: Modifier,
    changeDayOfWeek: (LocalDate) -> Unit,
    doneList: List<Habit>,
    navHostController: NavHostController,
    listsNotEmpty: Boolean,
    inProgressHabitList: List<Habit>,
    dayOfWeek: LocalDate,
    insertProgress: (HabitProgress) -> Unit
) {
    if (listsNotEmpty) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            ViewSwitcher(navHostController = navHostController)

            DayOfWeek(
                changeDayOfWeek = changeDayOfWeek
            )

            AnimatedVisibility(
                visible = inProgressHabitList.isNotEmpty() && LocalDate.now() <= dayOfWeek,
                exit = shrinkHorizontally(
                    shrinkTowards = Alignment.End
                )
            ) {
                InProgressHabits(
                    inProgressHabitList = inProgressHabitList,
                    insertProgress = insertProgress,
                    dayOfWeek = dayOfWeek
                )
            }

            if (doneList.isNotEmpty()) {
                DoneHabits(
                    doneList = doneList
                )
            }
        }
    } else {
        navHostController.navigate(Screen.MainScreen.route) {
            navHostController.graph.startDestinationRoute?.let { popUpTo(it) }
            launchSingleTop = true
        }
    }
}