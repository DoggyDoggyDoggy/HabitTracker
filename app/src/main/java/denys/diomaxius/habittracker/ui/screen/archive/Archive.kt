package denys.diomaxius.habittracker.ui.screen.archive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.ui.components.Loading
import denys.diomaxius.habittracker.ui.components.table.HabitTable
import denys.diomaxius.habittracker.ui.theme.HabitTrackerTheme
import java.time.LocalDate

@Composable
fun Archive(
    viewModel: ArchiveViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val habitList by viewModel.habitList.collectAsState()
    val habitProgressMap by viewModel.habitProgressMap.collectAsState()
    val yearList by viewModel.yearList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    //Category selection
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(LocalDate.now().year - 1) }

    Scaffold(
        topBar = {
            TopBar(
                yearList = yearList,
                chooseYear = { viewModel.getListOfHabitsByYear(it) },
                navHostController = navHostController,
                expanded = expanded,
                selected = selected,
                changeCategorySelection = { selected = it },
                toggleExpanded = { expanded = it }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier
                .padding(innerPadding),
            isLoading = isLoading,
            habitList = habitList,
            habitProgressMap = habitProgressMap
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    habitProgressMap: Map<Int, List<HabitProgress>>,
    habitList: List<Habit>,
    isLoading: Boolean
) {
    if (isLoading) {
        Loading()
    } else if (habitList.isEmpty()) {
        EmptyArchive()
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(habitList) { habit ->
                val habitProgress = habitProgressMap[habit.id] ?: emptyList()
                HabitTable(
                    habit = habit,
                    habitProgress = habitProgress
                )
            }
        }
    }
}

@Composable
fun EmptyArchive(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This year's archive is empty",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "You have no habit records from this year",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun TopBar(
    yearList: List<Int>,
    chooseYear: (Int) -> Unit,
    navHostController: NavHostController,
    expanded: Boolean,
    selected: Int,
    changeCategorySelection: (Int) -> Unit,
    toggleExpanded: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$selected",
                    style = MaterialTheme.typography.titleMedium
                )

                IconButton(onClick = { toggleExpanded(true) }) {
                    Icon(
                        if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = "Open menu"
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { toggleExpanded(false) }
                ) {
                    yearList.sorted().reversed().drop(1).forEach { year ->
                        DropdownMenuItem(
                            text = { Text(text = "$year") },
                            onClick = {
                                changeCategorySelection(year)
                                chooseYear(year)
                                toggleExpanded(false)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyArchive() {
    HabitTrackerTheme {
        EmptyArchive()
    }
}