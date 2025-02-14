package denys.diomaxius.habittracker.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.domain.state.HabitStateHolder
import denys.diomaxius.habittracker.domain.usecase.AddYearUseCase
import denys.diomaxius.habittracker.domain.usecase.CheckCurrentDateUseCase
import denys.diomaxius.habittracker.domain.usecase.GetHabitsUseCase
import denys.diomaxius.habittracker.domain.usecase.InsertHabitProgressUseCase
import denys.diomaxius.habittracker.domain.usecase.ObserveYearsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getHabitsUseCase: GetHabitsUseCase,
    private val observeYearsUseCase: ObserveYearsUseCase,
    private val addYearUseCase: AddYearUseCase,
    private val insertHabitProgressUseCase: InsertHabitProgressUseCase,
    private val checkCurrentDateUseCase: CheckCurrentDateUseCase
) : ViewModel() {

    val habitStateHolder = HabitStateHolder()

    private val _showArchiveIcon = MutableStateFlow(false)
    val showArchiveIcon = _showArchiveIcon.asStateFlow()

    init {
        viewModelScope.launch {
            getHabitsUseCase.execute(LocalDate.now().year).collect { (habits, progressMap) ->
                habitStateHolder.updateState(habits, progressMap)
            }
        }
        addYear()
    }

    private fun addYear() {
        viewModelScope.launch {
            addYearUseCase(LocalDate.now().year)
        }
        viewModelScope.launch {
            observeYearsUseCase().collectLatest { years ->
                _showArchiveIcon.value = years.size > 1
            }
        }
    }

    suspend fun checkTodayProgress(habitId: Int, date: LocalDate): Boolean {
        return checkCurrentDateUseCase(habitId, date) > 0
    }

    fun insertProgress(habitProgress: HabitProgress) {
        viewModelScope.launch {
            insertHabitProgressUseCase(habitProgress)
        }
    }
}