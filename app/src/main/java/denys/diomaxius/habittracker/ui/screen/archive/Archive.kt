package denys.diomaxius.habittracker.ui.screen.archive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.habittracker.ui.components.table.HabitTable
import java.time.LocalDate

@Composable
fun Archive(
    viewModel: ArchiveViewModel = hiltViewModel()
) {
    val habitList by viewModel.habitList.collectAsState()
    val habitProgressMap by viewModel.habitProgressMap.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val yearList by viewModel.yearList.collectAsState()



    Scaffold(
        topBar = {
            TopBar(
                yearList = yearList,
                chooseYear = {viewModel.getListOfHabitsByYear(it)}
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
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
fun TopBar(
    yearList: List<Int>,
    chooseYear: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        var expanded by remember { mutableStateOf(false) }
        var selected by remember { mutableStateOf(LocalDate.now().year) }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$selected")

            IconButton(onClick = { expanded = true }) {
                Icon(
                    if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Open menu"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                yearList.forEach { year ->
                    DropdownMenuItem(
                        text = { Text(text = "$year") },
                        onClick = {
                            selected = year
                            chooseYear(year)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}