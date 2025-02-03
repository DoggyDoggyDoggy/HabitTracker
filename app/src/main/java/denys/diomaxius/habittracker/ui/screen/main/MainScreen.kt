package denys.diomaxius.habittracker.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.habittracker.ui.screen.main.components.HabitTable

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val habitList by viewModel.habitList.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        habitList.forEach { habit ->
            val habitProgress by viewModel.getProgressByHabit(habit.id).collectAsState(emptyList())
            HabitTable(
                habit = habit,
                habitProgress = habitProgress,
                insertProgress = { viewModel.insertProgress(it) }
            )
        }
    }
}