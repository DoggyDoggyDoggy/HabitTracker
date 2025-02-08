package denys.diomaxius.habittracker.ui.screen.addHabitTable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                repeat(5) { boxId ->
                    ColorBox(
                        boxId = boxId,
                        themeId = themeId,
                        onColorChange = onColorChange
                    )
                }
            }
        }
    }
}

@Composable
fun ColorBox(
    boxId: Int,
    themeId: Int,
    onColorChange: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .size(32.dp)
            .clickable { onColorChange(boxId) }
            .background(TableThemes.tableThemes[boxId].themeButton),
        contentAlignment = Alignment.Center
    ) {
        if (themeId == boxId) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .size(14.dp)
                    .background(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewThemeTable() {
    ThemeTable(
        onColorChange = {},
        themeId = 0
    )
}