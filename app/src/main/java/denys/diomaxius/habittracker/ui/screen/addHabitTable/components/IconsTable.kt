package denys.diomaxius.habittracker.ui.screen.addHabitTable.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import denys.diomaxius.habittracker.ui.icons.IconData

@Composable
fun IconsTable(
    modifier: Modifier = Modifier,
    onIconChange: (Int) -> Unit,
    iconId: Int
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
                    IconButton(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = if (iconId == it) Color.Black else Color.LightGray
                            ),
                        onClick = {
                            onIconChange(it)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = IconData.icons[it]),
                            contentDescription = "Icon",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
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