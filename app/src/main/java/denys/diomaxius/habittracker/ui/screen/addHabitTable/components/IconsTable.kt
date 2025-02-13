package denys.diomaxius.habittracker.ui.screen.addHabitTable.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import denys.diomaxius.habittracker.data.constants.IconData

@Composable
fun IconsTable(
    modifier: Modifier = Modifier,
    onIconChange: (Int) -> Unit,
    iconId: Int
) {
    Column(
        modifier = modifier
    ) {
        repeat(3) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5) { inconIndex ->
                    CustomIconButton(
                        iconIndex = when (row) {
                            0 -> inconIndex
                            1 -> inconIndex + 5
                            else -> inconIndex + 10
                        },
                        onIconChange = onIconChange,
                        iconId = iconId
                    )
                }
            }
        }
    }
}

@Composable
fun CustomIconButton(
    iconIndex: Int,
    onIconChange: (Int) -> Unit,
    iconId: Int
) {
    IconButton(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (iconId == iconIndex) Color.Black else Color.LightGray
            ),
        onClick = {
            onIconChange(iconIndex)
        }
    ) {
        Icon(
            painter = painterResource(id = IconData.icons[iconIndex]),
            contentDescription = "Icon",
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIconsTable() {
    IconsTable(
        onIconChange = {},
        iconId = 0
    )
}