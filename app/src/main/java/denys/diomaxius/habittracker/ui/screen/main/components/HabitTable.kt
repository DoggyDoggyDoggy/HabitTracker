package denys.diomaxius.habittracker.ui.screen.main.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.ui.icons.IconData
import denys.diomaxius.habittracker.ui.tableThemes.TableThemes
import kotlinx.coroutines.delay
import java.time.LocalDate

@Composable
fun HabitTable(
    habit: Habit,
    habitProgress: List<HabitProgress>,
    insertProgress: (HabitProgress) -> Unit,
    checkTodayProgress: suspend (Int, LocalDate) -> Boolean
) {
    var isHabitTrackedForToday by remember(habit.id) { mutableStateOf(false) }
    var currentDate by remember(habit.id) { mutableStateOf(LocalDate.now()) }

    LaunchedEffect(habit.id, currentDate) {
        isHabitTrackedForToday = checkTodayProgress(habit.id, currentDate)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(30_000) //30 seconds
            val newDate = LocalDate.now()
            if (newDate != currentDate) {
                currentDate = newDate
            }
        }
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
                    painter = painterResource(id = IconData.icons[habit.iconId]),
                    contentDescription = "Habit icon",
                    tint = Color.Unspecified
                )

                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = habit.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = TableThemes.tableThemes[habit.colorTheme].fontColor
                    )

                    if (habit.description.isNotEmpty()) {
                        Text(
                            text = habit.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = TableThemes.tableThemes[habit.colorTheme].fontColor
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

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
fun CheckedIcon(
    insertProgress: (HabitProgress) -> Unit,
    isHabitTrackedForToday: Boolean,
    toggleTracked: () -> Unit,
    habitId: Int,
    habitColorTheme: Int
) {
    var playAnimation by remember {
        mutableStateOf(false)
    }

    val scale by animateFloatAsState(
        targetValue = if (playAnimation) 1.2f else 1f,
        animationSpec = tween(durationMillis = 350, easing = LinearEasing),
        label = "",
        finishedListener = {playAnimation = false}
    )

    IconButton(
        modifier = Modifier.scale(scale),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = checkIconColor(isHabitTrackedForToday, habitColorTheme)
        ),
        onClick = {
            playAnimation = !isHabitTrackedForToday
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
            contentDescription = "Check",
            tint = checkIconTint(isHabitTrackedForToday, habitColorTheme)
        )
    }
}

fun checkIconColor(
    isHabitTrackedForToday: Boolean,
    habitColorTheme: Int
): Color {
    return if (isHabitTrackedForToday) {
        TableThemes.tableThemes[habitColorTheme].checkedIcon
    } else {
        TableThemes.tableThemes[habitColorTheme].unCheckedIcon
    }
}

fun checkIconTint(
    isHabitTrackedForToday: Boolean,
    habitColorTheme: Int
): Color {
    return if (isHabitTrackedForToday) {
        TableThemes.tableThemes[habitColorTheme].iconTintChecked
    } else {
        Color.Black
    }
}

@Preview
@Composable
fun PreviewHabitTable() {
    HabitTable(
        habit = dummyHabit,
        habitProgress = dummyHabitProgress,
        insertProgress = {},
        checkTodayProgress = { _, _ -> false }
    )
}

