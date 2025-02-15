package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.repository.HabitRepository
import javax.inject.Inject

class InsertHabitUseCase @Inject constructor(
    private val habitRepository: HabitRepository
){
    suspend operator fun invoke(habit: Habit) {
        habitRepository.insertHabit(habit)
    }
}