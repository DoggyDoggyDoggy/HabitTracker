package denys.diomaxius.habittracker.data.model

import androidx.compose.ui.graphics.Color

data class TableTheme(
    val fontColor: Color,
    val iconTintChecked: Color = Color.Black,
    val themeButton: Color,
    val tableColor: Color,
    val boxColorUnchecked: Color,
    val boxColorChecked: Color,
    val checkedIcon: Color,
    val unCheckedIcon: Color
)