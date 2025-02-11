package denys.diomaxius.habittracker.ui.screen.main

import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import java.time.LocalDate

val dummyHabit = Habit(
    id = 1,
    name = "Example",
    iconId = 0,
    description = "Dummy description",
    category = "",
    colorTheme = 0,
    year = 0
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