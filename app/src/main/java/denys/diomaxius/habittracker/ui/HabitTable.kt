package denys.diomaxius.habittracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout

@Composable
fun HabitTable(

) {
    MonthsGrid(days = 31)
}

@Composable
fun MonthsGrid(
    modifier: Modifier = Modifier,
    spacing: Int = 4,
    days: Int
) {
    Layout(
        modifier = modifier,
        content = {
            repeat(days) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color.Blue)
                )
            }
        }
    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
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
