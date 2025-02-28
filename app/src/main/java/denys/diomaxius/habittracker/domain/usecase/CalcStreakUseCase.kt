package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.model.HabitProgress
import denys.diomaxius.habittracker.domain.repository.HabitProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class CalcStreakUseCase @Inject constructor(
    private val habitProgressRepository: HabitProgressRepository
) {
    operator fun invoke(habitId: Int): Flow<Int> {
        return habitProgressRepository.getProgressByHabit(habitId)
            .map { progressList ->
                calculateStreak(progressList)
            }
    }

    private fun calculateStreak(progressList: List<HabitProgress>) : Int {
        val completedRecords = progressList
            .filter { it.isCompleted }
            .sortedByDescending { it.date }

        var streak = 0
        var currentDate = LocalDate.now()

        for (progress in completedRecords) {
            val diffDays = ChronoUnit.DAYS.between(progress.date, currentDate).toInt()
            if (diffDays == 0 || diffDays == 1) {
                streak++
                currentDate = progress.date
            } else {
                break
            }
        }
        return streak
    }
}