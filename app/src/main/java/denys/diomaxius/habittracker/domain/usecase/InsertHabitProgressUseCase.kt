package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.model.HabitProgress
import denys.diomaxius.habittracker.domain.repository.HabitProgressRepository
import javax.inject.Inject

class InsertHabitProgressUseCase @Inject constructor(
    private val habitProgressRepository: HabitProgressRepository
) {
    suspend operator fun invoke(habitProgress: HabitProgress) {
        habitProgressRepository.insertProgress(habitProgress)
    }
}