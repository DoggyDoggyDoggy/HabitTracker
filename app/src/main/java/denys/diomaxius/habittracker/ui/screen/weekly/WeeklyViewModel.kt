package denys.diomaxius.habittracker.ui.screen.weekly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.usecase.CheckCurrentDateUseCase
import denys.diomaxius.habittracker.domain.usecase.GetHabitsByYearUseCase
import denys.diomaxius.habittracker.domain.usecase.ObserveYearsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeeklyViewModel @Inject constructor(
    private val getHabitsByYearUseCase: GetHabitsByYearUseCase,
    private val checkCurrentDateUseCase: CheckCurrentDateUseCase,
    private val observeYearsUseCase: ObserveYearsUseCase
) : ViewModel() {
    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())

    private val _doneHabitList = MutableStateFlow<List<Habit>>(emptyList())
    val doneHabitList = _doneHabitList.asStateFlow()

    private val _inProgressHabitList = MutableStateFlow<List<Habit>>(emptyList())
    val inProgressHabitList = _inProgressHabitList.asStateFlow()

    private val _dayOfWeek = MutableStateFlow<LocalDate>(LocalDate.now())
    val dayOfWeek = _dayOfWeek.asStateFlow()

    //Move to another ViewModel
    private val _showArchiveIcon = MutableStateFlow(false)
    val showArchiveIcon = _showArchiveIcon.asStateFlow()

    private val _showEditIcon = MutableStateFlow(true)
    val showEditIcon = _showEditIcon.asStateFlow()

    init {
        getHabitList()
        observeYear()
        getInProgressHabitList()
    }

    private fun getInProgressHabitList() {
        viewModelScope.launch {
            combine(_habitList, _doneHabitList) { allHabits, doneHabits ->
                allHabits - doneHabits.toSet()
            }.collect { filteredList ->
                _inProgressHabitList.value = filteredList
            }
        }
    }
    private fun observeYear() {
        viewModelScope.launch {
            observeYearsUseCase().collectLatest { years ->
                _showArchiveIcon.value = years.size > 1
            }
        }
    }

    fun changeDayOfWeek(date: LocalDate) {
        _dayOfWeek.value = date
        viewModelScope.launch {
            getDoneHabit(_dayOfWeek.value)
        }
    }

    private fun getHabitList(date: LocalDate = _dayOfWeek.value) {
        viewModelScope.launch {
            getHabitsByYearUseCase(LocalDate.now().year).collect {
                _habitList.value = it
                _showEditIcon.value = _habitList.value.isNotEmpty()
                getDoneHabit(date)
            }
        }
    }

    private suspend fun getDoneHabit(date: LocalDate) {
        val newList = mutableListOf<Habit>()
        _doneHabitList.value = emptyList()
        _habitList.value.forEach { habit ->
            if (checkCurrentDateUseCase(habit.id, date) == 1) {
                newList.add(habit)
                _doneHabitList.value = newList.toList()
            }
        }
    }
}