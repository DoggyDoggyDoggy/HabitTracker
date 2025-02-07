package denys.diomaxius.habittracker.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.data.repository.HabitProgressRepository
import denys.diomaxius.habittracker.data.repository.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
    private val habitProgressRepository: HabitProgressRepository
) : ViewModel() {

    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())
    val habitList = _habitList.asStateFlow()

    private val _habitProgressMap = MutableStateFlow<Map<Int, List<HabitProgress>>>(emptyMap())
    val habitProgressMap: StateFlow<Map<Int, List<HabitProgress>>> = _habitProgressMap

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                habitRepository.getAllHabits(),
                habitProgressRepository.getAllProgress()
            ) { habits, progress ->
                habits to progress.groupBy { it.habitId }
            }.collect { (habits, progressMap) ->
                _habitList.value = habits
                _habitProgressMap.value = progressMap
                _isLoading.value = false
            }
        }
    }

    suspend fun checkTodayProgress(habitId: Int, date: LocalDate): Boolean {
        return habitProgressRepository.checkCurrentDate(habitId, date) > 0
    }

    fun insertProgress(habitProgress: HabitProgress) {
        viewModelScope.launch {
            habitProgressRepository.insertProgress(habitProgress)
        }
    }
}