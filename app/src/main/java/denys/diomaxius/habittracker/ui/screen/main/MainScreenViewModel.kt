package denys.diomaxius.habittracker.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.data.repository.HabitProgressRepository
import denys.diomaxius.habittracker.data.repository.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
    private val habitProgressRepository: HabitProgressRepository
) : ViewModel() {

    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())
    val habitList = _habitList.asStateFlow()

    private val _habitProgress = MutableStateFlow<List<HabitProgress>>(emptyList())
    val habitProgress = _habitProgress.asStateFlow()

    init {
        viewModelScope.launch {
            habitRepository.getAllHabits().collect { _habitList.value = it }
        }

        viewModelScope.launch {
            habitProgressRepository.getAllProgress().collect{_habitProgress.value = it}
        }
    }

    fun addHabitProgress(habitProgress: HabitProgress) {
        viewModelScope.launch{
            habitProgressRepository.insertProgress(habitProgress)
        }
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            habitRepository.insertHabit(habit)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            habitRepository.deleteHabit(habit)
        }
    }
}