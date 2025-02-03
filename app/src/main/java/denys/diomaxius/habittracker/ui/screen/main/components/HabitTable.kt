package denys.diomaxius.habittracker.ui.screen.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import java.time.LocalDate

@Composable
fun HabitTable(
    habit: Habit,
    habitProgress: List<HabitProgress>,
    insertProgress: (HabitProgress) -> Unit
) {
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                Icon(
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            end = 5.dp
                        )
                        .clickable {
                            insertProgress(
                                HabitProgress(
                                    habit.id,
                                    date = LocalDate.now(),
                                    isCompleted = true
                                )
                            )
                        },
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
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
        insertProgress = {}
    )
}

