package denys.diomaxius.habittracker.ui.screen.main.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import java.time.LocalDate

@Composable
fun HabitTable(
    habit: Habit,
    habitProgress: List<HabitProgress>,
    insertProgress: (HabitProgress) -> Unit,
    checkTodayProgress: suspend (Int, LocalDate) -> Boolean
) {
    var isHabitTrackedForToday by remember { mutableStateOf(false) }

    LaunchedEffect(isHabitTrackedForToday) {
        isHabitTrackedForToday = checkTodayProgress(habit.id, LocalDate.now())
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = habit.name
                )

                Spacer(modifier = Modifier.weight(1f))

                CheckedIcon(
                    habitId = habit.id,
                    insertProgress = insertProgress,
                    isHabitTrackedForToday = isHabitTrackedForToday,
                    toggleTracked = { isHabitTrackedForToday = !isHabitTrackedForToday }
                )
            }

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                HabitGrid(
                    habitProgress = habitProgress
                )
            }
        }
    }
}

@Composable
fun CheckedIcon(
    insertProgress: (HabitProgress) -> Unit,
    isHabitTrackedForToday: Boolean,
    toggleTracked: () -> Unit,
    habitId: Int
) {
    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = if (isHabitTrackedForToday) Color.Gray else Color.White
        ),
        onClick = {
                insertProgress(
                    HabitProgress(
                        habitId = habitId,
                        date = LocalDate.now(),
                        isCompleted = !isHabitTrackedForToday
                    )
                )
                toggleTracked()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Check"
        )
    }
}

@Preview
@Composable
fun PreviewHabitTable() {
    HabitTable(
        habit = dummyHabit,
        habitProgress = dummyHabitProgress,
        insertProgress = {},
        checkTodayProgress = { _, _ -> false },
    )
}

