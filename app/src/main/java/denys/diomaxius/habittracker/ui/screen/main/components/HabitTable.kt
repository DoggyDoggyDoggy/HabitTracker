package denys.diomaxius.habittracker.ui.screen.main.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress

@Composable
fun HabitTable(habit: Habit, habitProgress: List<HabitProgress>) {
    Card(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = habit.name
            )

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                HabitGrid(
                    days = 365,
                    habitProgress = habitProgress
                )
            }
        }
    }
}

