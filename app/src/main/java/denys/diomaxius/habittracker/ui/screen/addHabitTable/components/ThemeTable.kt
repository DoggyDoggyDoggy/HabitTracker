package denys.diomaxius.habittracker.ui.screen.addHabitTable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import denys.diomaxius.habittracker.ui.tableThemes.TableThemes

@Composable
fun ThemeTable(
    modifier: Modifier = Modifier,
    onColorChange: (Int) -> Unit,
    themeId: Int
) {
    Column(
        modifier = modifier
    ) {
        repeat(1) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { onColorChange(it) }
                            .background(TableThemes.tableThemes[it].themeButton)
                            .border(
                                width = 1.dp,
                                color = if (themeId == it) Color.Black else Color.LightGray
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewThemeTable() {
    ThemeTable(
        onColorChange = {},
        themeId = 0)
}