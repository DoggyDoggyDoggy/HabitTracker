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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.LocalDate

val week = listOf(6, 5, 4, 3, 2, 1, 0)

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
        week.forEach { weekDayOrder ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp)
                    .padding(2.dp)
                    .border(1.dp, Color.Black, RectangleShape)
                    .background(Color.Cyan)
                    .clickable {
                        changeDayOfWeek(
                            LocalDate
                                .now()
                                .minusDays(weekDayOrder.toLong())
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "${
                            LocalDate.now().minusDays(weekDayOrder.toLong()).dayOfWeek.name[0]
                        }"
                    )

                    Text(
                        text = "${
                            LocalDate.now().minusDays(weekDayOrder.toLong()).dayOfMonth
                        }"
                    )
                }
            }
        }
    }
}