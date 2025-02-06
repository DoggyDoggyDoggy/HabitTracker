package denys.diomaxius.habittracker.ui.screen.addHabitTable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.R
import denys.diomaxius.habittracker.data.constants.CategoryData
import denys.diomaxius.habittracker.data.constants.IconData
import denys.diomaxius.habittracker.data.model.Habit

@Composable
fun AddHabitTable(
    viewModel: ViewModelAddHabitTable = hiltViewModel(),
    navHostController: NavHostController
) {
    val name = viewModel.name.value
    val description = viewModel.description.value
    val category = viewModel.category.value
    val iconId = viewModel.iconId.value

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

        OutlinedTextField(
            value = description,
            onValueChange = { viewModel.onDescriptionChanged(it) },
            label = {
                Text(text = "Enter your description for habit")
            }
        )

        DropDownMenu(
            category = category,
            onCategoryChange = { viewModel.onCategoryChanged(it) }
        )

        IconsTable(
            onIconChange = {viewModel.onIconIdChange(it)}
        )

        Button(
            onClick = {
                viewModel.addHabit(
                    Habit(
                        name = name,
                        iconResId = IconData.icons[iconId],
                        category = category,
                        description = description
                    )
                )
                navHostController.popBackStack()
            }
        ) {
            Text(text = "Add table")
        }
    }
}

@Composable
fun IconsTable(
    onIconChange: (Int) -> Unit
) {
    Column {
        repeat(1){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5){
                    IconButton(onClick = { onIconChange(it) }
                    ) {
                        Icon(
                            painter = painterResource(id = IconData.icons[it]),
                            contentDescription = "Icon",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    options: List<String> = CategoryData.categories,
    category: String,
    onCategoryChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = category,
                onValueChange = {},
                readOnly = true,
                label = { Text("Choose category") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onCategoryChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}