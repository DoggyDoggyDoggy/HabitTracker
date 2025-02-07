package denys.diomaxius.habittracker.ui.screen.main.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.ui.tableThemes.TableThemes
import java.time.LocalDate

@Composable
fun HabitTable(
    habit: Habit,
    habitProgress: List<HabitProgress>,
    insertProgress: (HabitProgress) -> Unit,
    checkTodayProgress: suspend (Int, LocalDate) -> Boolean,
    deleteHabit: () -> Unit
) {
    var isHabitTrackedForToday by remember(habit.id) { mutableStateOf(false) }

    LaunchedEffect(habit.id) {
        isHabitTrackedForToday = checkTodayProgress(habit.id, LocalDate.now())
    }

    Card(
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = TableThemes.tableThemes[habit.colorTheme].tableColor
        )
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
                Icon(
                    modifier = Modifier.size(42.dp),
                    painter = painterResource(id = habit.iconResId),
                    contentDescription = "Habit icon",
                    tint = Color.Unspecified
                )

                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = habit.name
                    )
                    if (habit.description.isNotEmpty()){
                        Text(
                            text = habit.description
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                DeleteIcon(
                    deleteHabit = deleteHabit
                )

                CheckedIcon(
                    habitId = habit.id,
                    insertProgress = insertProgress,
                    isHabitTrackedForToday = isHabitTrackedForToday,
                    toggleTracked = { isHabitTrackedForToday = !isHabitTrackedForToday },
                    habitColorTheme = habit.colorTheme
                )
            }

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                HabitGrid(
                    habitProgress = habitProgress,
                    boxColorUnchecked = TableThemes.tableThemes[habit.colorTheme].boxColorUnchecked,
                    boxColorChecked = TableThemes.tableThemes[habit.colorTheme].boxColorChecked
                )
            }
        }
    }
}

@Composable
fun DeleteIcon(deleteHabit: () -> Unit) {
    IconButton(onClick = deleteHabit) {
        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete habit table")
    }
}

@Composable
fun CheckedIcon(
    insertProgress: (HabitProgress) -> Unit,
    isHabitTrackedForToday: Boolean,
    toggleTracked: () -> Unit,
    habitId: Int,
    habitColorTheme: Int
) {
    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = checkedIconColor(isHabitTrackedForToday, habitColorTheme)
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

fun checkedIconColor(
    isHabitTrackedForToday: Boolean,
    habitColorTheme: Int
): Color {
    return if (isHabitTrackedForToday) {
        TableThemes.tableThemes[habitColorTheme].checkedIcon
    } else {
        TableThemes.tableThemes[habitColorTheme].unCheckedIcon
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
        deleteHabit = {}
    )
}

