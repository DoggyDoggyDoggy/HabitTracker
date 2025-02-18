package denys.diomaxius.habittracker.ui.components.topbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.domain.usecase.GetHabitsByYearUseCase
import denys.diomaxius.habittracker.domain.usecase.ObserveYearsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor(
    private val getHabitsByYearUseCase: GetHabitsByYearUseCase,
    private val observeYearsUseCase: ObserveYearsUseCase
) : ViewModel() {

    private val _showArchiveIcon = MutableStateFlow(false)
    val showArchiveIcon = _showArchiveIcon.asStateFlow()

    private val _showEditIcon = MutableStateFlow(true)
    val showEditIcon = _showEditIcon.asStateFlow()

    init {
        viewModelScope.launch {
            observeYearsUseCase().collectLatest { years ->
                _showArchiveIcon.value = years.size > 1
            }
        }
        viewModelScope.launch {
            getHabitsByYearUseCase(LocalDate.now().year).collect {
                _showEditIcon.value = it.isNotEmpty()
            }
        }
    }
}