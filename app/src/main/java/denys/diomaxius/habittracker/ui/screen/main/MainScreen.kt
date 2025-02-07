package denys.diomaxius.habittracker.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.navigation.Screen
import denys.diomaxius.habittracker.ui.screen.main.components.HabitTable
import java.time.LocalDate

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val habitList by viewModel.habitList.collectAsState()
    val habitProgressMap by viewModel.habitProgressMap.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                navHostController = navHostController
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            habitList = habitList,
            habitProgressMap = habitProgressMap,
            insertProgress = { viewModel.insertProgress(it) },
            checkTodayProgress = { id, date -> viewModel.checkTodayProgress(id, date) }
        )
    }
}


@Composable
fun Content(
    modifier: Modifier = Modifier,
    habitList: List<Habit>,
    habitProgressMap: Map<Int, List<HabitProgress>>,
    insertProgress: (HabitProgress) -> Unit,
    checkTodayProgress: suspend (Int, LocalDate) -> Boolean
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(habitList) { habit ->
            val habitProgress = habitProgressMap[habit.id] ?: emptyList()

            HabitTable(
                habit = habit,
                habitProgress = habitProgress,
                insertProgress = insertProgress,
                checkTodayProgress = checkTodayProgress
            )
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
            navHostController.navigate(Screen.EditHabitTable.route) { launchSingleTop = true }
        }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit habits")
        }
        IconButton(onClick = {
            navHostController.navigate(Screen.AddHabitTable.route) { launchSingleTop = true }
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add new habit")
        }
    }
}