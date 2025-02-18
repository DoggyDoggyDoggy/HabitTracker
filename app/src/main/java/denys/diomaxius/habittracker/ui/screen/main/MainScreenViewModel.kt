package denys.diomaxius.habittracker.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.domain.model.HabitProgress
import denys.diomaxius.habittracker.domain.state.HabitStateHolder
import denys.diomaxius.habittracker.domain.usecase.AddYearUseCase
import denys.diomaxius.habittracker.domain.usecase.CheckCurrentDateUseCase
import denys.diomaxius.habittracker.domain.usecase.GetHabitsWithProgressUseCase
import denys.diomaxius.habittracker.domain.usecase.InsertHabitProgressUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getHabitsWithProgressUseCase: GetHabitsWithProgressUseCase,
    private val addYearUseCase: AddYearUseCase,
    private val insertHabitProgressUseCase: InsertHabitProgressUseCase,
    private val checkCurrentDateUseCase: CheckCurrentDateUseCase
) : ViewModel() {

    val habitStateHolder = HabitStateHolder()

    init {
        viewModelScope.launch {
            getHabitsWithProgressUseCase.execute(LocalDate.now().year).collect { (habits, progressMap) ->
                habitStateHolder.updateState(habits, progressMap)
            }
        }
        addYear()
    }

    private fun addYear() {
        viewModelScope.launch {
            addYearUseCase(LocalDate.now().year)
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