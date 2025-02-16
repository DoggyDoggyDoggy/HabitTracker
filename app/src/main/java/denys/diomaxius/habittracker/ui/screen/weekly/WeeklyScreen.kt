package denys.diomaxius.habittracker.ui.screen.weekly

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

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
                Row(
                ) {
                    Text(text = habit.name)
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
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp)
                    .padding(2.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Black, RectangleShape)
                    .clickable { changeDayOfWeek(date) },
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "${
                            date.dayOfWeek.name.take(3)
                        }".toLowerCase().capitalize()
                    )

                    Text(
                        text = "${
                            date.dayOfMonth
                        }"
                    )

                    Text(
                        text = "${
                            date.month.name.take(3)
                        }".toLowerCase().capitalize()
                    )
                }
            }
        }
    }
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