package denys.diomaxius.habittracker.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                navHostController = navHostController,
                habitListIsNotEmpty = habitList.isNotEmpty()
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            habitList = habitList,
            habitProgressMap = habitProgressMap,
            insertProgress = { viewModel.insertProgress(it) },
            checkTodayProgress = { id, date -> viewModel.checkTodayProgress(id, date) },
            navHostController = navHostController,
            isLoading = isLoading
        )
    }
}


@Composable
fun Content(
    modifier: Modifier = Modifier,
    habitList: List<Habit>,
    habitProgressMap: Map<Int, List<HabitProgress>>,
    insertProgress: (HabitProgress) -> Unit,
    checkTodayProgress: suspend (Int, LocalDate) -> Boolean,
    navHostController: NavHostController,
    isLoading: Boolean
) {
    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                Modifier.size(200.dp),
                strokeWidth = 18.dp
            )
        }
    } else {
        if (habitList.isNotEmpty()) {
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
        } else {
            EmptyHabitList(
                modifier = modifier,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun EmptyHabitList(
    modifier: Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "No habits found",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Start tracking your progress by adding a new habit!",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(5.dp))

        Button(onClick = {
            navHostController.navigate(Screen.AddHabitTable.route) {
                launchSingleTop = true
            }
        }) {
            Text(
                text = "Start Tracking",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    habitListIsNotEmpty: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = {
            navHostController.navigate(Screen.Archive.route) { launchSingleTop = true }
        }) {
            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Archive")
        }
        IconButton(
            enabled = habitListIsNotEmpty,
            onClick = {
                navHostController.navigate(Screen.EditHabitTable.route) { launchSingleTop = true }
            }
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit habits")
        }
        IconButton(onClick = {
            navHostController.navigate(Screen.AddHabitTable.route) { launchSingleTop = true }
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add new habit")
        }
    }
}