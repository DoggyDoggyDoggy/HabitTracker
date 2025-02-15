package denys.diomaxius.habittracker.domain.model

import java.time.LocalDate

data class HabitProgress(
    val habitId: Int,
    val date: LocalDate,
    val isCompleted: Boolean
)
