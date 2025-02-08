package denys.diomaxius.habittracker.ui.screen.editHabitTable

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.navigation.Screen
import denys.diomaxius.habittracker.ui.icons.IconData
import denys.diomaxius.habittracker.ui.tableThemes.TableThemes

@Composable
fun EditHabitTable(
    viewModel: EditHabitTableViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val habitList by viewModel.habitList.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(habitList) { habit ->
            HabitTable(
                habit = habit,
                habitList = habitList,
                onDeleteTable = { viewModel.deleteHabit(it) },
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun HabitTable(
    modifier: Modifier = Modifier,
    habit: Habit,
    onDeleteTable: (Habit) -> Unit,
    navHostController: NavHostController,
    habitList: List<Habit>
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

            Text(
                text = habit.name,
                style = MaterialTheme.typography.titleSmall,
                color = TableThemes.tableThemes[habit.colorTheme].fontColor
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    navHostController
                        .navigate("${Screen.AddHabitTable.route}?habitId=${habit.id}") {
                            launchSingleTop = true
                        }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }

            IconButton(
                onClick = {
                    onDeleteTable(habit)
                    if (habitList.size == 1) {
                        Log.i("hello there", "Asd")
                        navHostController.popBackStack()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHabitTable() {
    HabitTable(
        habit = Habit(
            name = "Example",
            description = "Description",
            colorTheme = 0,
            iconId = 0,
            category = ""
        ),
        onDeleteTable = {},
        navHostController = rememberNavController(),
        habitList = listOf()
    )
}