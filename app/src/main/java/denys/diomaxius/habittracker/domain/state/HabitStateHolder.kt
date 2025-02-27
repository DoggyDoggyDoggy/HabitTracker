package denys.diomaxius.habittracker.domain.state

import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.model.HabitProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HabitStateHolder {
    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())
    val habitList: StateFlow<List<Habit>> = _habitList.asStateFlow()

    private val _habitProgressMap = MutableStateFlow<Map<Int, List<HabitProgress>>>(emptyMap())
    val habitProgressMap: StateFlow<Map<Int, List<HabitProgress>>> = _habitProgressMap.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun updateState(habits: List<Habit>, progressMap: Map<Int, List<HabitProgress>>) {
        _habitList.value = habits
        _habitProgressMap.value = progressMap
        _isLoading.value = false
    }
}