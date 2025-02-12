package denys.diomaxius.habittracker.ui.screen.archive

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.data.repository.HabitProgressRepository
import denys.diomaxius.habittracker.data.repository.HabitRepository
import denys.diomaxius.habittracker.data.repository.YearStorageRepository
import denys.diomaxius.habittracker.ui.screen.main.MainScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
    private val habitProgressRepository: HabitProgressRepository,
    private val yearStorageRepository: YearStorageRepository
) : ViewModel(){
    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())
    val habitList = _habitList.asStateFlow()

    private val _habitProgressMap = MutableStateFlow<Map<Int, List<HabitProgress>>>(emptyMap())
    val habitProgressMap: StateFlow<Map<Int, List<HabitProgress>>> = _habitProgressMap

    private val _yearList = MutableStateFlow<List<Int>>(emptyList())
    val yearList = _yearList.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()


    init {
        viewModelScope.launch {
            yearStorageRepository.yearsFlow.collect{
                _yearList.value = it
            }
        }
        getListOfHabits()
    }

    fun getListOfHabitsByYear(year: Int) {
        getListOfHabits(year)
    }

    private fun getListOfHabits(year: Int = LocalDate.now().year - 1) {
        viewModelScope.launch {
            combine(
                habitRepository.getAllHabits(year),
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
}