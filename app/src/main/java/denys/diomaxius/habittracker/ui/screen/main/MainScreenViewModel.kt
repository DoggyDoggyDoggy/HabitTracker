package denys.diomaxius.habittracker.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.domain.model.HabitProgress
import denys.diomaxius.habittracker.domain.state.HabitStateHolder
import denys.diomaxius.habittracker.domain.usecase.AddYearUseCase
import denys.diomaxius.habittracker.domain.usecase.CalcStreakUseCase
import denys.diomaxius.habittracker.domain.usecase.CheckCurrentDateUseCase
import denys.diomaxius.habittracker.domain.usecase.GetHabitsWithProgressUseCase
import denys.diomaxius.habittracker.domain.usecase.InsertHabitProgressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getHabitsWithProgressUseCase: GetHabitsWithProgressUseCase,
    private val addYearUseCase: AddYearUseCase,
    private val insertHabitProgressUseCase: InsertHabitProgressUseCase,
    private val checkCurrentDateUseCase: CheckCurrentDateUseCase,
    private val calcStreakUseCase: CalcStreakUseCase
) : ViewModel() {

    val habitStateHolder = HabitStateHolder()

    private val _streakMap = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val streakMap: StateFlow<Map<Int, Int>> = _streakMap.asStateFlow()

    init {
        viewModelScope.launch {
            getHabitsWithProgressUseCase.execute(LocalDate.now().year)
                .collect { (habits, progressMap) ->
                    habitStateHolder.updateState(habits, progressMap)

                    habits.forEach { habit ->
                        launch {
                            calcStreakUseCase(habit.id).collect { streak ->
                                _streakMap.update { currentMap ->
                                    currentMap.toMutableMap().apply { put(habit.id, streak) }
                                }
                            }
                        }
                    }
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
        subscribeToStreak(habitId)
        return checkCurrentDateUseCase(habitId, date) > 0
    }

    private fun subscribeToStreak(habitId: Int) {
        viewModelScope.launch {
            calcStreakUseCase(habitId).collect { streak ->
                _streakMap.update { currentMap ->
                    currentMap.toMutableMap().apply { put(habitId, streak) }
                }
            }
        }
    }

    fun insertProgress(habitProgress: HabitProgress) {
        viewModelScope.launch {
            insertHabitProgressUseCase(habitProgress)
        }
    }


}