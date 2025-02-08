package denys.diomaxius.habittracker.ui.screen.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import denys.diomaxius.habittracker.data.model.HabitProgress

@Composable
fun HabitGrid(
    modifier: Modifier = Modifier,
    spacing: Int = 4,
    boxSize: Int = 16,
    fixHeight: Int = 1,
    days: Int = 365,
    rows: Int = 7,
    habitProgress: List<HabitProgress>,
    boxColorUnchecked: Color,
    boxColorChecked: Color
) {
    val density = LocalDensity.current.density

    Layout(
        modifier = modifier,
        content = {
            repeat(days) { day ->
                Box(
                    modifier = Modifier
                        .size(boxSize.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(
                            if (habitProgress.any { it.date.dayOfYear == (day + 1) && it.isCompleted })
                                boxColorChecked else boxColorUnchecked
                        )
                )
            }
        }
    ) { measurables, constraints ->
        layout(
            width = (((boxSize * density) + spacing) * days/rows + ((boxSize * density) + spacing)).toInt(),
            height = (((boxSize * density) + spacing - fixHeight) * rows).toInt()
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