package denys.diomaxius.habittracker.ui.screen.weekly

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.util.Locale

@Composable
fun DayOfWeek(
    changeDayOfWeek: (LocalDate) -> Unit
) {
    var currentDate by remember {
        mutableStateOf(LocalDate.now())
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            val newDate = LocalDate.now()
            if (currentDate != newDate) {
                currentDate = newDate
                changeDayOfWeek(currentDate)
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        calcDay().forEach { date ->
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp)
                    .padding(2.dp)
                    .clickable { changeDayOfWeek(date) },
                border = CardDefaults.outlinedCardBorder(true),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (date == currentDate) Color(0xFFB2E9AC)
                    else CardDefaults.cardColors().containerColor
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = nameOfDay(date)
                    )

                    Text(
                        text = "${date.dayOfMonth}"
                    )

                    Text(
                        text = nameOfMonths(date)
                    )
                }
            }
        }
    }
}

fun nameOfDay(date: LocalDate): String {
    return date.dayOfWeek.name.take(3).lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun nameOfMonths(date: LocalDate): String {
    return date.month.name.take(3).lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun calcDay(): List<LocalDate> {
    val monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))
    return (0..6).map { monday.plusDays(it.toLong()) }
}

@Preview(showBackground = true)
@Composable
fun PreviewDayOfWeek() {
    DayOfWeek(
        changeDayOfWeek = {}
    )
}