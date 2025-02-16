package denys.diomaxius.habittracker.ui.screen.weekly

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.habittracker.data.constants.IconData
import denys.diomaxius.habittracker.data.constants.TableThemes
import denys.diomaxius.habittracker.domain.model.Habit
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.util.Locale

@Composable
fun WeeklyScreen(
    viewModel: WeeklyViewModel = hiltViewModel()
) {
    val doneList by viewModel.doneHabitList.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DayOfWeek(
            changeDayOfWeek = { viewModel.changeDayOfWeek(it) }
        )

        LazyColumn() {
            items(doneList) { habit ->
                DoneHabitTable(habit = habit)
            }
        }
    }
}

@Composable
fun DoneHabitTable(
    modifier: Modifier = Modifier,
    habit: Habit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = TableThemes.tableThemes[habit.colorTheme].tableColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .size(42.dp),
                painter = painterResource(id = IconData.icons[habit.iconId]),
                contentDescription = "Icon",
                tint = Color.Unspecified
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = habit.name,
                    style = if (habit.colorTheme < 12) {
                        MaterialTheme.typography.titleSmall
                    } else {
                        MaterialTheme.typography.titleSmall.copy(shadow = null)
                    },
                    color = TableThemes.tableThemes[habit.colorTheme].fontColor
                )

                if (habit.description.isNotEmpty()) {
                    Text(
                        text = habit.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = TableThemes.tableThemes[habit.colorTheme].fontColor,
                        softWrap = true
                    )
                }
            }
        }
    }
}

@Composable
fun DayOfWeek(
    changeDayOfWeek: (LocalDate) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        calcDay().forEach { date ->
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp)
                    .padding(2.dp)
                    .clickable { changeDayOfWeek(date) },
                border = CardDefaults.outlinedCardBorder(true),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (date == LocalDate.now()) Color(0xFFB2E9AC)
                    else CardDefaults.cardColors().containerColor
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = nameOfDay(date)
                    )

                    Text(
                        text = "${date.dayOfMonth}"
                    )

                    Text(
                        text = nameOfMonths(date)
                    )
                }
            }
        }
    }
}

fun nameOfDay(date: LocalDate): String {
    return date.dayOfWeek.name.take(3).lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun nameOfMonths(date: LocalDate): String {
    return date.month.name.take(3).lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun calcDay(): List<LocalDate> {
    val monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))
    return (0..6).map { monday.plusDays(it.toLong()) }
}

@Preview(showBackground = true)
@Composable
fun PreviewDayOfWeek() {
    DayOfWeek(changeDayOfWeek = {})
}