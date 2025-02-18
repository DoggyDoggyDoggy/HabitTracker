package denys.diomaxius.habittracker.ui.screen.weekly

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import denys.diomaxius.habittracker.data.constants.IconData
import denys.diomaxius.habittracker.data.constants.TableThemes
import denys.diomaxius.habittracker.domain.model.Habit

@Composable
fun DoneHabits(doneList: List<Habit>) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF4CAF50)
            )
        ) {
            Text(
                modifier = Modifier
                    .padding(6.dp),
                text = "Done:",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }

        LazyColumn {
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
            .padding(
                vertical = 6.dp,
                horizontal = 14.dp
            ),
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

