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

    fun onTextChanged(newText: String) {
        _name.value = newText
    }
    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            habitRepository.insertHabit(habit)
        }
    }
}