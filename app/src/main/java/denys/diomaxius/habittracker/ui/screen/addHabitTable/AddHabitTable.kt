package denys.diomaxius.habittracker.ui.screen.addHabitTable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.data.model.Habit

@Composable
fun AddHabitTable(
    viewModel: ViewModelAddHabitTable = hiltViewModel(),
    navHostController: NavHostController
) {
    val name = viewModel.name.value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.onTextChanged(it) },
            label = {
                Text(text = "Enter your habit")
            }
        )

        Button(
            onClick = {
                viewModel.addHabit(Habit(name = name))
                navHostController.popBackStack()
            }
        ) {
            Text(text = "Add table")
        }
    }
}