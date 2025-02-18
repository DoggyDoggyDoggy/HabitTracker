package denys.diomaxius.habittracker.ui.screen.weekly

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import denys.diomaxius.habittracker.data.constants.IconData
import denys.diomaxius.habittracker.data.constants.TableThemes
import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.model.HabitProgress
import kotlinx.coroutines.delay
import java.time.LocalDate

@Composable
fun InProgressHabits(
    inProgressHabitList: List<Habit>,
    insertProgress: (HabitProgress) -> Unit,
    dayOfWeek: LocalDate
) {

    val pendingDeletions = remember { mutableStateListOf<Habit>() }

    LaunchedEffect(pendingDeletions.size) {
        if (pendingDeletions.isNotEmpty()) {
            delay(1700)
            pendingDeletions.forEach { habit ->
                insertProgress(
                    HabitProgress(
                        habitId = habit.id,
                        date = LocalDate.now(),
                        isCompleted = true
                    )
                )
            }
            pendingDeletions.clear()
        }
    }

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF3F51B5)
            )
        ) {
            Text(
                modifier = Modifier
                    .padding(6.dp),
                text = "In Progress:",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }

        LazyColumn {
            items(inProgressHabitList, key = { it.id }) { habit ->
                val isPendingDeletion = pendingDeletions.contains(habit)
                var visible by remember(habit.id) { mutableStateOf(!isPendingDeletion) }

                AnimatedVisibility(
                    visible = visible,
                    enter = EnterTransition.None,
                    exit = shrinkHorizontally(
                        animationSpec = tween(1500),
                        shrinkTowards = Alignment.End
                    )
                ) {
                    InProgressHabitTable(
                        habit = habit,
                        dayOfWeek = dayOfWeek,
                        toggleVisible = {
                            visible = false
                            if (!pendingDeletions.contains(habit)) {
                                pendingDeletions.add(habit)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun InProgressHabitTable(
    modifier: Modifier = Modifier,
    habit: Habit,
    dayOfWeek: LocalDate,
    toggleVisible: () -> Unit
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


            if (dayOfWeek == LocalDate.now()) {
                IconButton(
                    modifier = Modifier.padding(end = 5.dp),
                    onClick = {
                        toggleVisible()
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = TableThemes.tableThemes[habit.colorTheme].unCheckedIcon
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Check"
                    )
                }
            }
        }
    }
}