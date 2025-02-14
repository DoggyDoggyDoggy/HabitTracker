package denys.diomaxius.habittracker.ui.screen.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.domain.state.HabitStateHolder
import denys.diomaxius.habittracker.domain.usecase.GetHabitsWithProgressUseCase
import denys.diomaxius.habittracker.domain.usecase.ObserveYearsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val getHabitsWithProgressUseCase: GetHabitsWithProgressUseCase,
    private val observeYearsUseCase: ObserveYearsUseCase
) : ViewModel() {

    val habitStateHolder = HabitStateHolder()

    private val _yearList = MutableStateFlow<List<Int>>(emptyList())
    val yearList = _yearList.asStateFlow()

    init {
        viewModelScope.launch {
            observeYearsUseCase().collect {
                _yearList.value = it
            }
        }
        getListOfHabitsByYear(year = LocalDate.now().year - 1)
    }

    fun getListOfHabitsByYear(year: Int) {
        viewModelScope.launch {
            getHabitsWithProgressUseCase.execute(year).collect { (habits, progressMap) ->
                habitStateHolder.updateState(habits, progressMap)
            }
        }
    }
}