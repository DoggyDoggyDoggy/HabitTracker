package denys.diomaxius.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import denys.diomaxius.habittracker.ui.HabitTable
import denys.diomaxius.habittracker.ui.theme.HabitTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitTrackerTheme {
                HabitTable()
            }
        }
    }
}