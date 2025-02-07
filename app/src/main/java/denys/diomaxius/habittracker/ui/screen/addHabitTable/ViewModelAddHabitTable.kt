package denys.diomaxius.habittracker.ui.screen.addHabitTable

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.repository.HabitRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelAddHabitTable @Inject constructor(
    private val habitRepository: HabitRepository
) : ViewModel() {
    private val _name = mutableStateOf("")
    val name: State<String> get() = _name

    private val _description = mutableStateOf("")
    val description: State<String> get() = _description

    private val _category = mutableStateOf("None")
    val category: State<String> get() = _category

    private val _iconId = mutableStateOf(0)
    val iconId: State<Int> get() = _iconId

    private val _themeId = mutableStateOf(0)
    val themeId: State<Int> get() = _themeId

    private val _nameFieldError = mutableStateOf(false)
    val nameFieldError: State<Boolean> get() = _nameFieldError

    fun onNameFieldErrorChange() {
        _nameFieldError.value = _name.value.isEmpty()
    }

    fun onIconIdChange(id: Int) {
        _iconId.value = id
    }

    fun onThemeIdChange(id: Int) {
        _themeId.value = id
    }

    fun onTextChanged(name: String) {
        _name.value = name
        onNameFieldErrorChange()
    }

    fun onCategoryChanged(category: String) {
        _category.value = category
    }

    fun onDescriptionChanged(description: String) {
        _description.value = description
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            habitRepository.insertHabit(habit)
        }
    }
}