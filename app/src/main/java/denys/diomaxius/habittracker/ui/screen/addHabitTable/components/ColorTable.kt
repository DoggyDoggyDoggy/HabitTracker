package denys.diomaxius.habittracker.ui.screen.addHabitTable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun ColorTable(
    modifier: Modifier = Modifier,
    onColorChange: (Int) -> Unit,
    themeId: Int
) {
    Column(
        modifier = modifier
    ) {
        repeat(3) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(6) { boxId ->
                    ColorBox(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        boxId = when (row) {
                            0 -> boxId
                            1 -> boxId + 6
                            else -> boxId + 12
                        },
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
    modifier: Modifier = Modifier,
    boxId: Int,
    themeId: Int,
    onColorChange: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .size(36.dp)
            .clickable { onColorChange(boxId) }
            .background(TableThemes.tableThemes[boxId].themeButton),
        contentAlignment = Alignment.Center
    ) {
        if (themeId == boxId) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(16.dp)
                    .background(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewThemeTable() {
    ColorTable(
        onColorChange = {},
        themeId = 0
    )
}