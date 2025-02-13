package denys.diomaxius.habittracker.ui.screen.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.data.repository.YearStorageRepository
import denys.diomaxius.habittracker.ui.state.HabitStateHolder
import denys.diomaxius.habittracker.usecase.GetHabitsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val getHabitsUseCase: GetHabitsUseCase,
    private val yearStorageRepository: YearStorageRepository
) : ViewModel() {

    val habitStateHolder = HabitStateHolder()

    private val _yearList = MutableStateFlow<List<Int>>(emptyList())
    val yearList = _yearList.asStateFlow()

    init {
        viewModelScope.launch {
            yearStorageRepository.yearsFlow.collect {
                _yearList.value = it
            }
        }
        getListOfHabitsByYear(year = LocalDate.now().year - 1)
    }

    fun getListOfHabitsByYear(year: Int) {
        viewModelScope.launch {
            getHabitsUseCase.execute(year).collect { (habits, progressMap) ->
                habitStateHolder.updateState(habits, progressMap)
            }
        }
    }
}