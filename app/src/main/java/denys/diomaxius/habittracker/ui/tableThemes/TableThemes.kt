package denys.diomaxius.habittracker.ui.tableThemes

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import denys.diomaxius.habittracker.data.model.TableTheme
import denys.diomaxius.habittracker.ui.screen.addHabitTable.components.ThemeTable
import denys.diomaxius.habittracker.ui.screen.main.components.HabitTable
import denys.diomaxius.habittracker.ui.screen.main.components.dummyHabit
import denys.diomaxius.habittracker.ui.screen.main.components.dummyHabitProgress

object TableThemes {
    val tableThemes = listOf<TableTheme>(
        //First Row
        TableTheme(
            themeButton = Color(0xFFABC4FF),
            tableColor = Color(0xFFA5BEFA),
            iconTintChecked = Color(0xFFFFFFFF),
            fontColor = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFE2EAFC),
            boxColorChecked = Color(0xFFC8B6FF),
            checkedIcon = Color(0xFF89D2A3),
            unCheckedIcon = Color(0xFFE2EAFC)
        ),
        TableTheme(
            themeButton = Color(0xFFA564D3),
            tableColor = Color(0xFFC879FF),
            iconTintChecked = Color(0xFFFFFFFF),
            fontColor = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFFCD6F9),
            boxColorChecked = Color(0xFFC19BFF),
            checkedIcon = Color(0xFF89D2A3),
            unCheckedIcon = Color(0xFFFCD6F9)
        ),
        TableTheme(
            themeButton = Color(0xFFAB87FF),
            tableColor = Color(0xFFAB87FF),
            iconTintChecked = Color(0xFFFFFFFF),
            fontColor = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFDBD0F5),
            boxColorChecked = Color(0xFF68C72E),
            checkedIcon = Color(0xFF89D2A3),
            unCheckedIcon = Color(0xFFDBD0F5)
        ),
        TableTheme(
            themeButton = Color(0xFF3F4238),
            tableColor = Color(0xFF6B705C),
            iconTintChecked = Color(0xFFFFFFFF),
            fontColor = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFFFFE8D6),
            boxColorUnchecked = Color(0xFFB7B7A4),
            unCheckedIcon = Color(0xFFB7B7A4),
            checkedIcon = Color(0xFF89D2A3)
        ),
        TableTheme(
            themeButton = Color(0xFF97A869),
            tableColor = Color(0xFFADC178),
            iconTintChecked = Color(0xFFFFFFFF),
            fontColor = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFFEE6055),
            boxColorUnchecked = Color(0xFFDDE5B6),
            unCheckedIcon = Color(0xFFDDE5B6),
            checkedIcon = Color(0xFF89D2A3)
        ),
        //Second Row
        TableTheme(
            themeButton = Color(0xFFC3A18E),
            tableColor = Color(0xFFC3A18E),
            fontColor = Color(0xFFFFFFFF),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFE3D5CA),
            boxColorChecked = Color(0xFFD5BDAF),
            checkedIcon = Color(0xFF89D2A3),
            unCheckedIcon = Color(0xFFE3D5CA)
        ),
        TableTheme(
            themeButton = Color(0xFF2E51A3),
            tableColor = Color(0xFF2E51A3),
            fontColor = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xD39971E4),
            boxColorChecked = Color(0xFF48248B),
            checkedIcon = Color(0xFF89D2A3),
            unCheckedIcon = Color(0xD39971E4)
        ),
        TableTheme(
            themeButton = Color(0xFF262A56),
            tableColor = Color(0xFF262A56),
            fontColor = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFE3CCAE),
            boxColorChecked = Color(0xFFB8621B),
            checkedIcon = Color(0xFF89D2A3),
            unCheckedIcon = Color(0xFFE3CCAE)
        ),
        TableTheme(
            themeButton = Color(0xFF3D5300),
            tableColor = Color(0xFF3D5300),
            fontColor = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFFFFE31A),
            boxColorUnchecked = Color(0xFFABBA7C),
            unCheckedIcon = Color(0xFFABBA7C),
            checkedIcon = Color(0xFF89D2A3)
        ),
        TableTheme(
            themeButton = Color(0xFFFF8F00),
            tableColor = Color(0xFFFF8F00),
            fontColor = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFF26355D),
            boxColorUnchecked = Color(0xFFFFDB00),
            unCheckedIcon = Color(0xFFFFDB00),
            checkedIcon = Color(0xFF89D2A3)
        ),
    )
}

@Preview
@Composable
fun PreviewHabitTable() {
    HabitTable(
        habit = dummyHabit,
        habitProgress = dummyHabitProgress,
        insertProgress = {},
        checkTodayProgress = { _, _ -> false }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewThemeTable() {
    ThemeTable(
        onColorChange = {},
        themeId = 0
    )
}