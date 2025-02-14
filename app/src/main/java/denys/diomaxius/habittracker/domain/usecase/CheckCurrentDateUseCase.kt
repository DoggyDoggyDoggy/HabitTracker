package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.repository.HabitProgressRepository
import java.time.LocalDate
import javax.inject.Inject

class CheckCurrentDateUseCase @Inject constructor(
    private val habitProgressRepository: HabitProgressRepository
) {
    suspend operator fun invoke(habitId: Int, date: LocalDate): Int =
        habitProgressRepository.checkCurrentDate(habitId, date)
}