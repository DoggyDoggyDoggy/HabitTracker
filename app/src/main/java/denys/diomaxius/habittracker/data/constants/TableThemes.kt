package denys.diomaxius.habittracker.data.constants

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import denys.diomaxius.habittracker.data.model.TableTheme
import denys.diomaxius.habittracker.ui.components.table.HabitGridConfig
import denys.diomaxius.habittracker.ui.screen.addHabitTable.components.ColorTable
import denys.diomaxius.habittracker.ui.components.table.InteractiveHabitTable
import denys.diomaxius.habittracker.ui.dummyHabit
import denys.diomaxius.habittracker.ui.dummyHabitProgress


const val checked = true

object TableThemes {
    private val checkedIcon = Color(0xFF25A244)

    val tableThemes = listOf<TableTheme>(
        //First Row
        TableTheme(
            themeButton = Color(0xFFABC4FF),
            tableColor = Color(0xFFA5BEFA),
            iconTintChecked = Color(0xFFFFFFFF),
            fontColor = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFE2EAFC),
            boxColorChecked = Color(0xFFC8B6FF),
            checkedIcon = checkedIcon,
            unCheckedIcon = Color(0xFFE2EAFC)
        ),
        TableTheme(
            themeButton = Color(0xFFA564D3),
            tableColor = Color(0xFFC879FF),
            iconTintChecked = Color(0xFFFFFFFF),
            fontColor = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFFCD6F9),
            boxColorChecked = Color(0xFFC19BFF),
            checkedIcon = checkedIcon,
            unCheckedIcon = Color(0xFFFCD6F9)
        ),
        TableTheme(
            themeButton = Color(0xFFAB87FF),
            tableColor = Color(0xFFAB87FF),
            iconTintChecked = Color(0xFFFFFFFF),
            fontColor = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFDBD0F5),
            boxColorChecked = Color(0xFF68C72E),
            checkedIcon = checkedIcon,
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
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFF97A869),
            tableColor = Color(0xFFADC178),
            iconTintChecked = Color(0xFFFFFFFF),
            fontColor = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFFEE6055),
            boxColorUnchecked = Color(0xFFDDE5B6),
            unCheckedIcon = Color(0xFFDDE5B6),
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFFC3A18E),
            tableColor = Color(0xFFC3A18E),
            fontColor = Color(0xFFFFFFFF),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFE3D5CA),
            boxColorChecked = Color(0xFFD5BDAF),
            checkedIcon = checkedIcon,
            unCheckedIcon = Color(0xFFE3D5CA)
        ),
        //Second Row
        TableTheme(
            themeButton = Color(0xFF2E51A3),
            tableColor = Color(0xFF2E51A3),
            fontColor = Color(0xFFFFFFFF),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xD39971E4),
            boxColorChecked = Color(0xFF48248B),
            checkedIcon = checkedIcon,
            unCheckedIcon = Color(0xD39971E4)
        ),
        TableTheme(
            themeButton = Color(0xFF262A56),
            tableColor = Color(0xFF262A56),
            fontColor = Color(0xFFFFFFFF),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorUnchecked = Color(0xFFE3CCAE),
            boxColorChecked = Color(0xFFB8621B),
            checkedIcon = checkedIcon,
            unCheckedIcon = Color(0xFFE3CCAE)
        ),
        TableTheme(
            themeButton = Color(0xFF3D5300),
            tableColor = Color(0xFF3D5300),
            fontColor = Color(0xFFFFFFFF),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFFFFE31A),
            boxColorUnchecked = Color(0xFFABBA7C),
            unCheckedIcon = Color(0xFFABBA7C),
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFFFF8F00),
            tableColor = Color(0xFFFF8F00),
            fontColor = Color(0xFFFFFFFF),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFF26355D),
            boxColorUnchecked = Color(0xFFFFDB00),
            unCheckedIcon = Color(0xFFFFDB00),
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFF1B263B),
            tableColor = Color(0xFF415A77),
            fontColor = Color(0xFFFFFFFF),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFF26355D),
            boxColorUnchecked = Color(0xFF778DA9),
            unCheckedIcon = Color(0xFF778DA9),
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFFD90429),
            tableColor = Color(0xFFEF233C),
            fontColor = Color(0xFFFFFFFF),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFF2B2D42),
            boxColorUnchecked = Color(0xFFEDF2F4),
            unCheckedIcon = Color(0xFFEDF2F4),
            checkedIcon = checkedIcon
        ),
        //Third Row
        TableTheme(
            themeButton = Color(0xFF5E548E),
            tableColor = Color(0xFFFFFFFF),
            fontColor = Color(0xFF000000),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFF5E548E),
            boxColorUnchecked = Color(0xB2E0C1E6),
            unCheckedIcon = Color(0xB2E0C1E6),
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFF1D2D44),
            tableColor = Color(0xFFFFFFFF),
            fontColor = Color(0xFF000000),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFF1D2D44),
            boxColorUnchecked = Color(0xBFB0C6E2),
            unCheckedIcon = Color(0xBFB0C6E2),
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFF8E9AAF),
            tableColor = Color(0xFFFFFFFF),
            fontColor = Color(0xFF000000),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFF8E9AAF),
            boxColorUnchecked = Color(0xFFDEE2FF),
            unCheckedIcon = Color(0xFFDEE2FF),
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFFC59E91),
            tableColor = Color(0xFFFFFFFF),
            fontColor = Color(0xFF000000),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFFAF887B),
            boxColorUnchecked = Color(0xD7EBD8D0),
            unCheckedIcon = Color(0xD7EBD8D0),
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFF707272),
            tableColor = Color(0xFFFFFFFF),
            fontColor = Color(0xFF000000),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFF707272),
            boxColorUnchecked = Color(0xBFD0CFCF),
            unCheckedIcon = Color(0xBFD0CFCF),
            checkedIcon = checkedIcon
        ),
        TableTheme(
            themeButton = Color(0xFFFF4D6D),
            tableColor = Color(0xFFFFFFFF),
            fontColor = Color(0xFF000000),
            iconTintChecked = Color(0xFFFFFFFF),
            boxColorChecked = Color(0xFFFF4D6D),
            boxColorUnchecked = Color(0xBFFFCCD5),
            unCheckedIcon = Color(0xBFFFCCD5),
            checkedIcon = checkedIcon
        ),
    )
}

@Preview
@Composable
fun PreviewHabitTable() {
    val habitGridConfig = HabitGridConfig(density = LocalDensity.current.density)

    InteractiveHabitTable(
        habit = dummyHabit,
        habitProgress = dummyHabitProgress,
        insertProgress = {},
        checkTodayProgress = { _, _ -> checked },
        habitGridConfig = habitGridConfig
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewThemeTable() {
    ColorTable(
        onColorChange = {},
        themeId = 0
    )
}