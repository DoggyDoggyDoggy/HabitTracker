package denys.diomaxius.habittracker.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.ui.screen.main.components.HabitTable
import java.time.LocalDate

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
                viewModel = viewModel,
                habit = habit,
                habitProgress = habitProgress,
                insertProgress = { viewModel.insertProgress(it) }
            )
        }
        Button(onClick = { viewModel.addHabit(Habit(name = "Example"))}) {
            Text(text = "Add habit")
        }
    }
}