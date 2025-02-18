package denys.diomaxius.habittracker.ui.screen.weekly

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DoneHabitsViewModel : ViewModel() {
    private val _visibleItems = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val visibleItems = _visibleItems.asStateFlow()

    fun setVisibility(habitId: Int, isVisible: Boolean) {
        _visibleItems.update { currentMap ->
            currentMap + (habitId to isVisible)
        }
    }
}
