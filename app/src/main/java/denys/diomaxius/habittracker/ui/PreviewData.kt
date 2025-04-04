package denys.diomaxius.habittracker.ui


import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.model.HabitProgress
import java.time.LocalDate

val dummyHabit = Habit(
    id = 1,
    name = "Example",
    iconId = 0,
    description = "Dummy description",
    category = "",
    colorTheme = 17,
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