package denys.diomaxius.habittracker.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.habittracker.data.model.Habit

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val habitList by viewModel.habitList.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(habitList) { habit ->
                Text(text = habit.name)
            }
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = {
                    viewModel.addHabit(Habit(name = "Example"))
                }
            ) {
                Text(text = "Add")
            }
        }
    }

}