package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.domain.repository.HabitRepository
import javax.inject.Inject

class UpdateHabitUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) {
        habitRepository.updateHabit(habit)
    }
}