package denys.diomaxius.habittracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.habittracker.navigation.Screen

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    habitListIsNotEmpty: Boolean,
    showArchiveIcon: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
          Text(
              text = "HabitTracker",
              style = MaterialTheme.typography.titleMedium
          )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            if (showArchiveIcon) {
                IconButton(onClick = {
                    navHostController.navigate(Screen.Archive.route) { launchSingleTop = true }
                }) {
                    Icon(imageVector = Icons.Default.Archive, contentDescription = "Archive")
                }
            }
            IconButton(
                enabled = habitListIsNotEmpty,
                onClick = {
                    navHostController.navigate(Screen.EditHabitTable.route) {
                        launchSingleTop = true
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit habits")
            }
            IconButton(onClick = {
                navHostController.navigate(Screen.AddHabitTable.route) { launchSingleTop = true }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new habit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    TopBar(
        navHostController = rememberNavController(),
        habitListIsNotEmpty = true,
        showArchiveIcon = false

    )
}