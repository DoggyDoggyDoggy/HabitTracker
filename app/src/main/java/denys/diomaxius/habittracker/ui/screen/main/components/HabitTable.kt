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
    var isTracked by remember { mutableStateOf(false) }

    LaunchedEffect(isTracked) {
        isTracked = checkTodayProgress(habit.id, LocalDate.now())
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

                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (isTracked) Color.Gray else Color.White
                    ),
                    onClick = {if (!isTracked) {
                        insertProgress(
                            HabitProgress(
                                habit.id,
                                date = LocalDate.now(),
                                isCompleted = true
                            )
                        )
                        isTracked = true
                    } else {
                        insertProgress(
                            HabitProgress(
                                habit.id,
                                date = LocalDate.now(),
                                isCompleted = false
                            )
                        )
                        isTracked = false
                    }

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Check"
                    )
                }
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

@Preview
@Composable
fun PreviewHabitTable() {
    HabitTable(
        habit = habit,
        habitProgress = habitProgress,
        insertProgress = {},
        checkTodayProgress = { _, _ -> false},
    )
}

