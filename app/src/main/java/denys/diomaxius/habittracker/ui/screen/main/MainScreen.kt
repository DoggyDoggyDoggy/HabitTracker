package denys.diomaxius.habittracker.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.navigation.Screen
import denys.diomaxius.habittracker.ui.screen.main.components.HabitTable
import java.time.LocalDate

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val habitList by viewModel.habitList.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                navHostController = navHostController
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(habitList) { habit ->
                val habitProgress by viewModel.getProgressByHabit(habit.id)
                    .collectAsState(emptyList())
                HabitTable(
                    habit = habit,
                    deleteHabit = { viewModel.deleteHabit(habit) },
                    habitProgress = habitProgress,
                    insertProgress = { viewModel.insertProgress(it) },
                    checkTodayProgress = { id: Int, date: LocalDate ->
                        viewModel.checkTodayProgress(
                            id,
                            date
                        )
                    }
                )
            }
        }
    }


}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = {
            navHostController.navigate(Screen.AddHabitTable.route) { launchSingleTop = true }
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add new habit")
        }
    }
}