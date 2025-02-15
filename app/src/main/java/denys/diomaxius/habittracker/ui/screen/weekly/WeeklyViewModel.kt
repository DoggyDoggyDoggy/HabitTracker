package denys.diomaxius.habittracker.ui.screen.weekly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.usecase.CheckCurrentDateUseCase
import denys.diomaxius.habittracker.domain.usecase.GetHabitsByYearUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeeklyViewModel @Inject constructor(
    private val getHabitsByYearUseCase: GetHabitsByYearUseCase,
    private val checkCurrentDateUseCase: CheckCurrentDateUseCase
) : ViewModel() {
    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())

    private val _doneHabitList = MutableStateFlow<List<Habit>>(emptyList())
    val doneHabitList = _doneHabitList.asStateFlow()

    init {
        getHabitList()
    }

    fun changeDayOfWeek(date: LocalDate) {
        _doneHabitList.value = emptyList()
        viewModelScope.launch {
            getDoneHabit(date)
        }
    }

    private fun getHabitList(date: LocalDate  = LocalDate.now()) {
        viewModelScope.launch {
            getHabitsByYearUseCase(LocalDate.now().year).collect {
                _habitList.value = it
                getDoneHabit(date)
            }
        }
    }

    private suspend fun getDoneHabit(date: LocalDate) {
        val newList = mutableListOf<Habit>()
        _habitList.value.forEach { habit ->
            if (checkCurrentDateUseCase(habit.id, date) == 1) {
                newList.add(habit)
                _doneHabitList.value = newList.toList()
            }
        }
    }
}