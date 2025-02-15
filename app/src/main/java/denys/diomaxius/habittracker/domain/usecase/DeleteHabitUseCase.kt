package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.repository.HabitRepository
import javax.inject.Inject

class DeleteHabitUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) {
        habitRepository.deleteHabit(habit)
    }
}