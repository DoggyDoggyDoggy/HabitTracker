package denys.diomaxius.habittracker.ui.tableThemes

import androidx.compose.ui.graphics.Color
import denys.diomaxius.habittracker.data.model.TableTheme

object TableThemes {
    val tableThemes = listOf<TableTheme>(
        TableTheme(
            themeButton = Color(0xFFABC4FF),
            tableColor = Color(0xFFB6CCFE),
            boxColorUnchecked = Color(0xFFE2EAFC),
            boxColorChecked = Color(0xFFC8B6FF)
        ),
        TableTheme(
            themeButton = Color(0xFFA564D3),
            tableColor = Color(0xFFC879FF),
            boxColorUnchecked = Color(0xFFFCD6F9),
            boxColorChecked = Color(0xFFC19BFF)
        ),
        TableTheme(
            themeButton = Color.Yellow,
            tableColor = Color.Yellow,
            boxColorUnchecked = Color.Gray,
            boxColorChecked = Color.Yellow
        ),
        TableTheme(
            themeButton = Color.Cyan,
            tableColor = Color.Cyan,
            boxColorUnchecked = Color.Gray,
            boxColorChecked = Color.Yellow
        ),
        TableTheme(
            themeButton = Color.Magenta,
            tableColor = Color.Magenta,
            boxColorUnchecked = Color.Gray,
            boxColorChecked = Color.Yellow
        ),
    )
}