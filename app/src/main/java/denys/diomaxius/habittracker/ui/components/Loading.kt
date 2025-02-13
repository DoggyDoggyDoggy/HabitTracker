package denys.diomaxius.habittracker.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import denys.diomaxius.habittracker.ui.theme.HabitTrackerTheme

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier= Modifier.padding(start = 12.dp),
            text = "Loading...",
            style = MaterialTheme.typography.titleLarge
            )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoading() {
    HabitTrackerTheme {
        Loading()
    }
}