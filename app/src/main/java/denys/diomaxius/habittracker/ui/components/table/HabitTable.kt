package denys.diomaxius.habittracker.ui.components.table

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.ui.icons.IconData
import denys.diomaxius.habittracker.ui.screen.main.dummyHabit
import denys.diomaxius.habittracker.ui.screen.main.dummyHabitProgress
import denys.diomaxius.habittracker.ui.tableThemes.TableThemes
import kotlinx.coroutines.delay
import java.time.LocalDate

@Composable
fun HabitTable(
    habit: Habit,
    habitProgress: List<HabitProgress>,
    insertProgress: ((HabitProgress) -> Unit)? = null,
    checkTodayProgress: (suspend (Int, LocalDate) -> Boolean)? = null
) {
    val habitGridConfig = HabitGridConfig(density = LocalDensity.current.density)

    if (insertProgress != null && checkTodayProgress != null) {
        InteractiveHabitTable(
            habit = habit,
            habitProgress = habitProgress,
            insertProgress = insertProgress,
            checkTodayProgress = checkTodayProgress,
            habitGridConfig = habitGridConfig
        )
    } else {
        NonInteractiveHabitTable(
            habit = habit,
            habitProgress = habitProgress,
            habitGridConfig = habitGridConfig)
    }
}


@Composable
fun InteractiveHabitTable(
    habit: Habit,
    habitProgress: List<HabitProgress>,
    insertProgress: (HabitProgress) -> Unit,
    checkTodayProgress: suspend (Int, LocalDate) -> Boolean,
    habitGridConfig: HabitGridConfig
    ) {

    var currentDate by remember(habit.id) { mutableStateOf(LocalDate.now()) }
    val scroll =
        rememberScrollState(habitGridConfig.getInitialScrollPosition(currentDate.monthValue))
    var isHabitTrackedForToday by remember(habit.id) { mutableStateOf(false) }


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
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
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


                CheckedIcon(
                    modifier = Modifier.padding(end = 5.dp),
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
                    .horizontalScroll(scroll)
            ) {
                HabitGrid(
                    config = habitGridConfig,
                    habitProgress = habitProgress,
                    boxColorUnchecked = TableThemes.tableThemes[habit.colorTheme].boxColorUnchecked,
                    boxColorChecked = TableThemes.tableThemes[habit.colorTheme].boxColorChecked
                )
            }
        }
    }
}

@Composable
fun NonInteractiveHabitTable(
    habit: Habit,
    habitProgress: List<HabitProgress>,
    habitGridConfig: HabitGridConfig
) {
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
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
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
            }

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                HabitGrid(
                    config = habitGridConfig,
                    habitProgress = habitProgress,
                    boxColorUnchecked = TableThemes.tableThemes[habit.colorTheme].boxColorUnchecked,
                    boxColorChecked = TableThemes.tableThemes[habit.colorTheme].boxColorChecked
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHabitTable() {
    val habitGridConfig = HabitGridConfig(density = LocalDensity.current.density)

    InteractiveHabitTable(
        habit = dummyHabit,
        habitProgress = dummyHabitProgress,
        insertProgress = {},
        checkTodayProgress = { _, _ -> false },
        habitGridConfig = habitGridConfig
    )
}

