package denys.diomaxius.habittracker.ui.utils

import androidx.compose.ui.graphics.Color
import denys.diomaxius.habittracker.data.constants.TableThemes

fun checkIconColor(
    isHabitTracked: Boolean,
    habitColorTheme: Int
): Color {
    return if (isHabitTracked) {
        TableThemes.tableThemes[habitColorTheme].checkedIcon
    } else {
        TableThemes.tableThemes[habitColorTheme].unCheckedIcon
    }
}

fun checkIconTint(
    isHabitTracked: Boolean,
    habitColorTheme: Int
): Color {
    return if (isHabitTracked) {
        TableThemes.tableThemes[habitColorTheme].iconTintChecked
    } else {
        Color.Black
    }
}