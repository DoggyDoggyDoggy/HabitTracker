package denys.diomaxius.habittracker.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import java.time.LocalDate

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val habitList by viewModel.habitList.collectAsState()
    val habitProgress by viewModel.habitProgress.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(habitList) { habit ->
                Row {
                    Text(text = "${habit.id}")
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = habit.name)
                }
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(habitProgress) { habitProgress ->
                Row {
                    Text(text = "${habitProgress.habitId}")
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "${habitProgress.date}")
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "${habitProgress.isCompleted}")
                }
            }
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Button(
                    onClick = {
                        viewModel.addHabit(Habit(name = "Example"))
                    }
                ) {
                    Text(text = "Add")
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = {
                        viewModel.addHabitProgress(
                            HabitProgress(
                                habitId = 1,
                                date = LocalDate.now(),
                                isCompleted = true
                            )
                        )
                    }
                ) {
                    Text(text = "Add Progress")
                }
            }
        }
    }

}