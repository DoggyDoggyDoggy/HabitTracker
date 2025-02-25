package denys.diomaxius.habittracker.ui.screen.weekly

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
                changeDayOfWeek = changeDayOfWeek,
                dayOfWeek = dayOfWeek
            )


            BoxWithConstraints(
                modifier = Modifier.weight(1f)
            ) {
                val availableHeight = maxHeight
                Column(modifier = Modifier.fillMaxSize()) {
                    AnimatedVisibility(
                        modifier = Modifier.heightIn(max = availableHeight / 2),
                        visible = inProgressHabitList.isNotEmpty() && LocalDate.now() <= dayOfWeek,
                        exit = shrinkHorizontally(shrinkTowards = Alignment.End)
                    ) {
                        InProgressHabits(
                            modifier = Modifier.fillMaxWidth(),
                            inProgressHabitList = inProgressHabitList,
                            insertProgress = insertProgress,
                            dayOfWeek = dayOfWeek
                        )
                    }

                    if (doneList.isNotEmpty()) {
                        Box(modifier = Modifier.weight(1f)) {
                            DoneHabits(
                                modifier = Modifier.fillMaxSize(),
                                doneList = doneList
                            )
                        }
                    }
                }
            }
        }

        if (doneList.isEmpty() && LocalDate.now() > dayOfWeek) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Haven't done anything on that day",
                    style = MaterialTheme.typography.bodyLarge
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