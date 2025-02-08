package denys.diomaxius.habittracker.ui.screen.addHabitTable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.ui.screen.addHabitTable.components.CategoryDropdown
import denys.diomaxius.habittracker.ui.screen.addHabitTable.components.IconsTable
import denys.diomaxius.habittracker.ui.screen.addHabitTable.components.ColorTable

@Composable
fun AddHabitTable(
    viewModel: ViewModelAddHabitTable = hiltViewModel(),
    navHostController: NavHostController,
    habitId: Int?
) {
    if (habitId != null && !viewModel.isHabitLoaded.value) {
        viewModel.getHabitById(habitId)
    }
    val name = viewModel.name.value
    val description = viewModel.description.value
    val category = viewModel.category.value
    val iconId = viewModel.iconId.value
    val themeId = viewModel.themeId.value
    val nameFieldError = viewModel.nameFieldError.value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextField(
            isError = nameFieldError,
            value = name,
            onValueChange = {
                if (it.length <= 16)
                    viewModel.onNameChanged(it)
            },
            label = {
                Text(text = "Enter your habit")
            }
        )

        OutlinedTextField(
            value = description,
            onValueChange = {
                if (it.length <= 23)
                    viewModel.onDescriptionChanged(it)
            },
            label = {
                Text(text = "Enter your description for habit")
            }
        )

        CategoryDropdown(
            category = category,
            onCategoryChange = { viewModel.onCategoryChanged(it) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        IconsTable(
            onIconChange = { viewModel.onIconIdChange(it) },
            iconId = iconId
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        ColorTable(
            onColorChange = { viewModel.onThemeIdChange(it) },
            themeId = themeId
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.onNameFieldErrorChange()
                if (!nameFieldError && name.isNotEmpty()) {
                    if (habitId != null) {
                        viewModel.addHabit(
                            Habit(
                                id = habitId,
                                name = name,
                                iconId = iconId,
                                category = category,
                                description = description,
                                colorTheme = themeId
                            )
                        )
                    } else {
                        viewModel.addHabit(
                            Habit(
                                name = name,
                                iconId = iconId,
                                category = category,
                                description = description,
                                colorTheme = themeId
                            )
                        )
                    }
                    navHostController.popBackStack()
                }

            }
        ) {
            Text(
                text = "Add table",
                style = MaterialTheme.typography.labelMedium
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))
    }
}