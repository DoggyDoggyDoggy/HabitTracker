package denys.diomaxius.habittracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {
                    navHostController.navigate(Screen.MainScreen.route) {
                        navHostController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { inclusive = true }
                        }
                        launchSingleTop = true
                    }
                },
            border = CardDefaults.outlinedCardBorder(
                true
            ),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (currentRoute == Screen.MainScreen.route) Color(0xFFB2E9AC)
                else CardDefaults.cardColors().containerColor
            )
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Annual"
            )
        }

        Card(
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {
                    navHostController.navigate(Screen.Weekly.route) {
                        launchSingleTop = true
                    }
                },
            border = CardDefaults.outlinedCardBorder(
                true
            ),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (currentRoute == Screen.Weekly.route) Color(0xFFB2E9AC)
                else CardDefaults.cardColors().containerColor
            )
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Weekly"
            )
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