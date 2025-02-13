package denys.diomaxius.habittracker.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.data.repository.HabitProgressRepository
import denys.diomaxius.habittracker.data.repository.YearStorageRepository
import denys.diomaxius.habittracker.ui.state.HabitStateHolder
import denys.diomaxius.habittracker.usecase.GetHabitsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getHabitsUseCase: GetHabitsUseCase,
    private val habitProgressRepository: HabitProgressRepository,
    private val yearStorageRepository: YearStorageRepository
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
            yearStorageRepository.addYear(LocalDate.now().year)
        }
        viewModelScope.launch {
            yearStorageRepository.yearsFlow.collectLatest { years ->
                _showArchiveIcon.value = years.size > 1
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