package denys.diomaxius.habittracker.ui.screen.editHabitTable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.repository.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditHabitTableViewModel @Inject constructor(
    private val habitRepository: HabitRepository
):ViewModel() {
    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())
    val habitList = _habitList.asStateFlow()

    init {
        viewModelScope.launch {
            habitRepository.getAllHabits().collect { _habitList.value = it }
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            habitRepository.deleteHabit(habit)
        }
    }
}