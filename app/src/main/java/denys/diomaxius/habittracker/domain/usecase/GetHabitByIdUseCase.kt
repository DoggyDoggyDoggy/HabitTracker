package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.domain.repository.HabitRepository
import javax.inject.Inject

class GetHabitByIdUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habitId: Int): Habit = habitRepository.getHabitById(habitId)
}