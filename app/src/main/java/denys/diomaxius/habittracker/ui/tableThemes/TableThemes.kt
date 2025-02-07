package denys.diomaxius.habittracker.ui.tableThemes

import androidx.compose.ui.graphics.Color
import denys.diomaxius.habittracker.data.model.TableTheme

object TableThemes {
    val tableThemes = listOf<TableTheme>(
        TableTheme(
            themeButton = Color(0xFFABC4FF),
            tableColor = Color(0xFFB6CCFE),
            boxColorUnchecked = Color(0xFFE2EAFC),
            boxColorChecked = Color(0xFFC8B6FF),
            checkedIcon = Color(0xFF89D2A3),
            unCheckedIcon = Color(0xFFE2EAFC)
        ),
        TableTheme(
            themeButton = Color(0xFFA564D3),
            tableColor = Color(0xFFC879FF),
            boxColorUnchecked = Color(0xFFFCD6F9),
            boxColorChecked = Color(0xFFC19BFF),
            checkedIcon = Color(0xFF89D2A3),
            unCheckedIcon = Color(0xFFFCD6F9)
        ),
        TableTheme(
            themeButton = Color(0xFFAB87FF),
            tableColor = Color(0xFFAB87FF),
            boxColorUnchecked = Color(0xFFDBD0F5),
            boxColorChecked = Color(0xFF68C72E),
            checkedIcon = Color(0xFF89D2A3),
            unCheckedIcon = Color(0xFFDBD0F5)
        ),
        TableTheme(
            themeButton = Color(0xFF3F4238),
            tableColor = Color(0xFF6B705C),
            boxColorChecked = Color(0xFFFFE8D6),
            boxColorUnchecked = Color(0xFFB7B7A4),
            unCheckedIcon = Color(0xFFB7B7A4),
            checkedIcon = Color(0xFF89D2A3)
        ),
        TableTheme(
            themeButton = Color(0xFF97A869),
            tableColor = Color(0xFFADC178),
            boxColorChecked = Color(0xFFEE6055),
            boxColorUnchecked = Color(0xFFDDE5B6),
            unCheckedIcon = Color(0xFFDDE5B6),
            checkedIcon = Color(0xFF89D2A3)
        ),
    )
}