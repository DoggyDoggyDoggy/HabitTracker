package denys.diomaxius.habittracker.ui.screen.editHabitTable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.usecase.DeleteHabitUseCase
import denys.diomaxius.habittracker.domain.usecase.GetHabitsByYearUseCase
import denys.diomaxius.habittracker.domain.usecase.UpdateHabitsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EditHabitTableViewModel @Inject constructor(
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val updateHabitsUseCase: UpdateHabitsUseCase,
    private val getHabitsByYearUseCase: GetHabitsByYearUseCase
):ViewModel() {
    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())
    val habitList = _habitList.asStateFlow()

    init {
        viewModelScope.launch {
           getHabitsByYearUseCase(LocalDate.now().year).collect { _habitList.value = it }
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            deleteHabitUseCase(habit)
        }
    }

    fun reorderHabits(fromIndex: Int, toIndex: Int) {
        viewModelScope.launch {
            val currentHabit = _habitList.value.toMutableList()
            if (fromIndex < 0 || toIndex < 0 || fromIndex >= currentHabit.size || toIndex >= currentHabit.size) {
                return@launch
            }

            // Move an element in a list
            val movedHabits = currentHabit.removeAt(fromIndex)
            currentHabit.add(toIndex, movedHabits)

            // Update the order: for each element, set a new position value
            val updatedHabits = currentHabit.mapIndexed { index, habit ->
                habit.copy(position = index)
            }

            // Updating local StateFlow
            _habitList.value = updatedHabits

            // Updating the order in the database
            updateHabitsUseCase(updatedHabits)
        }
    }
}