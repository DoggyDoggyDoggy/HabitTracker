package denys.diomaxius.habittracker.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.habittracker.navigation.Screen

@Composable
fun ViewSwitcher(
    navHostController: NavHostController
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedButton(
            modifier = Modifier
                .padding(start = 12.dp),
            onClick = {
                navHostController.navigate(Screen.MainScreen.route) {
                    navHostController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) { inclusive = true }
                    }
                    launchSingleTop = true
                }
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (currentRoute == Screen.MainScreen.route) Color(0xFFB0F1EC)
                else ButtonDefaults.outlinedButtonColors().containerColor
            )
        ) {
            Text(text = "Annual")
        }

        OutlinedButton(
            modifier = Modifier
                .padding(start = 12.dp),
            onClick = {
                navHostController.navigate(Screen.Weekly.route) {
                    launchSingleTop = true
                }
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (currentRoute == Screen.Weekly.route) Color(0xFFB0F1EC)
                else ButtonDefaults.outlinedButtonColors().containerColor
            )
        ) {
            Text(text = "Weekly")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewViewSwitcher() {
    ViewSwitcher(
        navHostController = rememberNavController()
    )
}