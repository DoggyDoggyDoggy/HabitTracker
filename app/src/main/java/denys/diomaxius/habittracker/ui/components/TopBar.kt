package denys.diomaxius.habittracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
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
        horizontalArrangement = Arrangement.End
    ) {
        if (showArchiveIcon){
            IconButton(onClick = {
                navHostController.navigate(Screen.Archive.route) { launchSingleTop = true }
            }) {
                Icon(imageVector = Icons.Default.Archive, contentDescription = "Archive")
            }
        }
        IconButton(
            enabled = habitListIsNotEmpty,
            onClick = {
                navHostController.navigate(Screen.EditHabitTable.route) { launchSingleTop = true }
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