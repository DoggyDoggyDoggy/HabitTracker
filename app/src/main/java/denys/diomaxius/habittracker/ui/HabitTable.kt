package denys.diomaxius.habittracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.ui.screen.main.MainScreenViewModel

@Composable
fun HabitTable(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val habitList by viewModel.habitList.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        habitList.forEach { habit ->
            val habitProgress by viewModel.getProgressByHabit(habit.id).collectAsState(emptyList())
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
                        MonthsGrid(
                            days = 365,
                            habitProgress = habitProgress
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MonthsGrid(
    modifier: Modifier = Modifier,
    spacing: Int = 4,
    boxSize: Int = 16,
    fixHeight: Int = 1,
    days: Int,
    habitProgress: List<HabitProgress>
) {
    val density = LocalDensity.current.density

    Layout(
        modifier = modifier,
        content = {
            repeat(days) { day ->
                Box(
                    modifier = Modifier
                        .size(boxSize.dp)
                        .background(
                            if (habitProgress.any { it.date.dayOfYear == (day + 1) }) Color.Blue else Color.Gray
                        )
                )
            }
        }
    ) { measurables, constraints ->
        layout(
            width = constraints.maxWidth,
            height = (((boxSize * density) + spacing - fixHeight) * 7).toInt()
        ) {
            var x = 0
            var y = 0
            var index = 1
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            placeables.forEach { placeable ->
                placeable.placeRelative(x = x, y = y)
                if (index % 7 == 0) {
                    x += placeable.width + spacing
                    y = 0
                } else {
                    y += placeable.height + spacing
                }
                index++

            }
        }
    }
}