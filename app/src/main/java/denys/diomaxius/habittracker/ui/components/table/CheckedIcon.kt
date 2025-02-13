package denys.diomaxius.habittracker.ui.components.table

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.data.constants.TableThemes
import java.time.LocalDate

@Composable
fun CheckedIcon(
    modifier: Modifier = Modifier,
    insertProgress: (HabitProgress) -> Unit,
    isHabitTrackedForToday: Boolean,
    toggleTracked: () -> Unit,
    habitId: Int,
    habitColorTheme: Int
) {
    var playAnimation by remember {
        mutableStateOf(false)
    }

    val scale by animateFloatAsState(
        targetValue = if (playAnimation) 1.2f else 1f,
        animationSpec = tween(durationMillis = 350, easing = LinearEasing),
        label = "",
        finishedListener = { playAnimation = false }
    )

    IconButton(
        modifier = modifier.scale(scale),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = checkIconColor(isHabitTrackedForToday, habitColorTheme)
        ),
        onClick = {
            playAnimation = !isHabitTrackedForToday
            insertProgress(
                HabitProgress(
                    habitId = habitId,
                    date = LocalDate.now(),
                    isCompleted = !isHabitTrackedForToday
                )
            )
            toggleTracked()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Check",
            tint = checkIconTint(isHabitTrackedForToday, habitColorTheme)
        )
    }
}

fun checkIconColor(
    isHabitTrackedForToday: Boolean,
    habitColorTheme: Int
): Color {
    return if (isHabitTrackedForToday) {
        TableThemes.tableThemes[habitColorTheme].checkedIcon
    } else {
        TableThemes.tableThemes[habitColorTheme].unCheckedIcon
    }
}

fun checkIconTint(
    isHabitTrackedForToday: Boolean,
    habitColorTheme: Int
): Color {
    return if (isHabitTrackedForToday) {
        TableThemes.tableThemes[habitColorTheme].iconTintChecked
    } else {
        Color.Black
    }
}