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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.data.constants.IconData
import denys.diomaxius.habittracker.data.constants.TableThemes
import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.model.HabitProgress
import denys.diomaxius.habittracker.navigation.Screen
import denys.diomaxius.habittracker.ui.components.topbar.TopBar
import denys.diomaxius.habittracker.ui.components.ViewSwitcher
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.util.Locale

@Composable
fun WeeklyScreen(
    viewModel: WeeklyViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val doneHabitList by viewModel.doneHabitList.collectAsState()
    val inProgressHabitList by viewModel.inProgressHabitList.collectAsState()
    val dayOfWeek by viewModel.dayOfWeek.collectAsState()
    val habitListIsNotEmpty by viewModel.habitListIsNotEmpty.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                navHostController = navHostController
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            changeDayOfWeek = { viewModel.changeDayOfWeek(it) },
            doneList = doneHabitList,
            navHostController = navHostController,
            listsNotEmpty = habitListIsNotEmpty,
            inProgressHabitList = inProgressHabitList,
            dayOfWeek = dayOfWeek,
            insertProgress = { viewModel.insertProgress(it) }
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    changeDayOfWeek: (LocalDate) -> Unit,
    doneList: List<Habit>,
    navHostController: NavHostController,
    listsNotEmpty: Boolean,
    inProgressHabitList: List<Habit>,
    dayOfWeek: LocalDate,
    insertProgress: (HabitProgress) -> Unit
) {
    if (listsNotEmpty) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            ViewSwitcher(navHostController = navHostController)

            DayOfWeek(
                changeDayOfWeek = changeDayOfWeek
            )

            if (inProgressHabitList.isNotEmpty() && dayOfWeek >= LocalDate.now()) {
                InProgressHabits(
                    inProgressHabitList = inProgressHabitList,
                    insertProgress = insertProgress,
                    dayOfWeek = dayOfWeek
                )
            }

            if (doneList.isNotEmpty()) {
                DoneHabits(
                    doneList = doneList
                )
            }
        }
    } else {
        navHostController.navigate(Screen.MainScreen.route) {
            navHostController.graph.startDestinationRoute?.let { popUpTo(it) }
            launchSingleTop = true
        }
    }

}

@Composable
fun InProgressHabits(
    inProgressHabitList: List<Habit>,
    insertProgress: (HabitProgress) -> Unit,
    dayOfWeek: LocalDate
) {
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
        items(inProgressHabitList) { habit ->
            InProgressHabitTable(
                habit = habit,
                insertProgress = insertProgress,
                dayOfWeek = dayOfWeek
            )
        }
    }
}

@Composable
fun DoneHabits(doneList: List<Habit>) {
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

@Composable
fun InProgressHabitTable(
    modifier: Modifier = Modifier,
    habit: Habit,
    insertProgress: (HabitProgress) -> Unit,
    dayOfWeek: LocalDate
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
                        insertProgress(
                            HabitProgress(
                                habitId = habit.id,
                                date = LocalDate.now(),
                                isCompleted = true
                            )
                        )

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

@Composable
fun DayOfWeek(
    changeDayOfWeek: (LocalDate) -> Unit
) {
    var currentDate by remember {
        mutableStateOf(LocalDate.now())
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            val newDate = LocalDate.now()
            if (currentDate != newDate) {
                currentDate = newDate
                changeDayOfWeek(currentDate)
            }
        }
    }

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
                    containerColor = if (date == currentDate) Color(0xFFB2E9AC)
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
    DayOfWeek(
        changeDayOfWeek = {}
    )
}