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

    fun onIconIdChange(id: Int) {
        _iconId.value = id
    }

    fun onTextChanged(newText: String) {
        _name.value = newText
    }

    fun onCategoryChanged(newText: String) {
        _category.value = newText
    }

    fun onDescriptionChanged(newText: String) {
        _description.value = newText
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            habitRepository.insertHabit(habit)
        }
    }
}