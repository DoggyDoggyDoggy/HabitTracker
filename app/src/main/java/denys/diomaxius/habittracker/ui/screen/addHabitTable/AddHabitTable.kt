package denys.diomaxius.habittracker.ui.screen.addHabitTable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.ui.icons.IconData
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.ui.screen.addHabitTable.components.CategoryDropdown
import denys.diomaxius.habittracker.ui.screen.addHabitTable.components.IconsTable
import denys.diomaxius.habittracker.ui.screen.addHabitTable.components.ThemeTable

@Composable
fun AddHabitTable(
    viewModel: ViewModelAddHabitTable = hiltViewModel(),
    navHostController: NavHostController
) {
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
        OutlinedTextField(
            isError = nameFieldError,
            value = name,
            onValueChange = {
                if (it.length <= 10)
                    viewModel.onTextChanged(it)
            },
            label = {
                Text(text = "Enter your habit")
            }
        )

        OutlinedTextField(
            value = description,
            onValueChange = {
                if (it.length <= 15)
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

        IconsTable(
            modifier = Modifier.padding(12.dp),
            onIconChange = { viewModel.onIconIdChange(it) },
            iconId = iconId
        )

        ThemeTable(
            modifier = Modifier.padding(12.dp),
            onColorChange = { viewModel.onThemeIdChange(it) },
            themeId = themeId
        )

        Button(
            onClick = {
                viewModel.onNameFieldErrorChange()
                if (!nameFieldError && name.isNotEmpty()) {
                    viewModel.addHabit(
                        Habit(
                            name = name,
                            iconResId = IconData.icons[iconId],
                            category = category,
                            description = description,
                            colorTheme = themeId
                        )
                    )
                    navHostController.popBackStack()
                }

            }
        ) {
            Text(text = "Add table")
        }
    }
}