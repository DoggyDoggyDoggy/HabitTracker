package denys.diomaxius.habittracker.ui.screen.main.components

import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import java.time.LocalDate

val dummyHabit = Habit(
    id = 1,
    name = "Example"
)

val dummyHabitProgress = listOf<HabitProgress>(
    HabitProgress(
        habitId = dummyHabit.id,
        date = LocalDate.now(),
        isCompleted = true
    ),
    HabitProgress(
        habitId = dummyHabit.id,
        date = LocalDate.now().minusDays(1),
        isCompleted = true
    ),
    HabitProgress(
        habitId = dummyHabit.id,
        date = LocalDate.now().plusDays(1),
        isCompleted = true
    )
)