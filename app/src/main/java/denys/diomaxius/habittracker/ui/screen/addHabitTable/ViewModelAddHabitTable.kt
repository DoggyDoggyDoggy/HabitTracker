package denys.diomaxius.habittracker.ui.screen.addHabitTable

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.usecase.GetHabitByIdUseCase
import denys.diomaxius.habittracker.domain.usecase.InsertHabitUseCase
import denys.diomaxius.habittracker.domain.usecase.UpdateHabitUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelAddHabitTable @Inject constructor(
    private val insertHabitUseCase: InsertHabitUseCase,
    private val getHabitByIdUseCase: GetHabitByIdUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase
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

    private val _isHabitLoaded = mutableStateOf(false)
    val isHabitLoaded: State<Boolean> get() = _isHabitLoaded

    fun onNameFieldErrorChange() {
        _nameFieldError.value = _name.value.isEmpty()
    }

    fun onIconIdChange(id: Int) {
        _iconId.value = id
    }

    fun onThemeIdChange(id: Int) {
        _themeId.value = id
    }

    fun onNameChanged(name: String) {
        _name.value = name.trimStart()
        onNameFieldErrorChange()
    }

    fun onCategoryChanged(category: String) {
        _category.value = category
    }

    fun onDescriptionChanged(description: String) {
        _description.value = description.trimStart()
    }

    fun getHabitById(habitId: Int) {
        if (!_isHabitLoaded.value) {
            viewModelScope.launch {
                val habit: Habit = getHabitByIdUseCase(habitId)
                _name.value = habit.name
                _description.value = habit.description
                _category.value = habit.category
                _iconId.value = habit.iconId
                _themeId.value = habit.colorTheme
                _iconId.value = habit.iconId
                _isHabitLoaded.value = true
            }
        }
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            insertHabitUseCase(habit)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            updateHabitUseCase(habit)
        }
    }
}