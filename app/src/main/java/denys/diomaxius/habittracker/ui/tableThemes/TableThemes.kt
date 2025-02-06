package denys.diomaxius.habittracker.ui.tableThemes

import androidx.compose.ui.graphics.Color
import denys.diomaxius.habittracker.data.model.TableTheme

object TableThemes {
    val tableThemes = listOf<TableTheme>(
        TableTheme(
            themeButton = Color.Green,
            tableColor = Color.Green,
            boxColorUnchecked = Color.Gray,
            boxColorChecked = Color.Yellow
        ),
        TableTheme(
            themeButton = Color.Red,
            tableColor = Color.Red,
            boxColorUnchecked = Color.Gray,
            boxColorChecked = Color.Yellow
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