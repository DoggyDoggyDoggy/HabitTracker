package denys.diomaxius.habittracker.ui.screen.archive

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.data.repository.HabitProgressRepository
import denys.diomaxius.habittracker.data.repository.HabitRepository
import denys.diomaxius.habittracker.data.repository.YearStorageRepository
import denys.diomaxius.habittracker.ui.screen.main.MainScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    habitRepository: HabitRepository,
    habitProgressRepository: HabitProgressRepository,
    private val yearStorageRepository: YearStorageRepository
) : MainScreenViewModel(
    habitRepository, habitProgressRepository, yearStorageRepository
) {
    private val _yearList = MutableStateFlow<List<Int>>(emptyList())
    val yearList = _yearList.asStateFlow()

    init {
        viewModelScope.launch {
            yearStorageRepository.yearsFlow.collect{
                _yearList.value = it
            }
        }
    }

    fun getListOfHabitsByYear(year: Int) {
        getListOfHabits(year)
    }
}